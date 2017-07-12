/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.checkoutservice;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.civicxpress.dbconnectionservice.DBConnectionService;
import com.tekdog.dbutils. * ;
import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.file.model.DownloadResponse;

import com.civicxpress.formservice.FormService;
import com.civicxpress.letters.Cx2DataAccess;
import com.civicxpress.PaymentReceipt;
import com.civicxpress.ReceiptPdf;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.ChargeOutcome;
import com.stripe.net.RequestOptions;

//import com.civicxpress.checkoutservice.model.*;
/**
 * This is a singleton class with all its public methods exposed as REST APIs via generated controller class.
 * To avoid exposing an API for a particular public method, annotate it with @HideFromClient.
 *
 * Method names will play a major role in defining the Http Method for the generated APIs. For example, a method name
 * that starts with delete/remove, will make the API exposed as Http Method "DELETE".
 *
 * Method Parameters of type primitives (including java.lang.String) will be exposed as Query Parameters &
 * Complex Types/Objects will become part of the Request body in the generated API.
 */
@ExposeToClient
public class CheckoutService {

	private static final Logger logger = LoggerFactory.getLogger(CheckoutService.class);
	private static final String RESET_NOTIFICATION_MAIL_ID = "civicxpress@gmail.com ";
	private static final String RESET_NOTIFICATION_MAIL_PASSWORD = "civicxpress2016!";

	@Autowired
	private SecurityService securityService;

	@Autowired
	private FormService formService;

	public void municipalityCheckout(Long municipalityId, String paymentMethod, String paymentNumber, BigDecimal amountReceived, String comments, Long[] feeIds, HttpServletRequest request) throws Exception {
		DBQueryParams queryParams = new DBQueryParams();
		Connection connection = DBConnectionService.getConnection();
		CallableStatement checkoutStatement = null;
		Long transactionId = null;

		queryParams.addString("comments", comments);

		BigDecimal amountDue = DBUtils.selectOne(connection, "SELECT SUM(Amount) as amountDue FROM Fees WHERE ID IN (" + StringUtils.join(feeIds, ',') + ")", null).getBigDecimal("amountDue");

		if (amountReceived.compareTo(amountDue) == -1) { // Ensure we are not getting swindled.
			throw new Exception("The amount received is not enough to pay for the chosen fees.");
		}

		int currentUserId = Integer.parseInt(securityService.getUserId());

		System.out.println("amountReceived: " + amountReceived);
		System.out.println("amountDue: " + amountDue);

		try {
			Boolean transactionSuccess = false;
			connection.setAutoCommit(false);

			if (paymentMethod.equals("Credit Card")) {
				transactionSuccess = chargeCreditCard(amountReceived, "Municipality fees: " + comments, paymentNumber);
			} else {
				transactionSuccess = true;
			}

			if (transactionSuccess) {
				checkoutStatement = connection.prepareCall("{call municipalityCheckout(?,?,?,?,?,?)}");
				checkoutStatement.setQueryTimeout(60);
				checkoutStatement.setString("paymentMethod", paymentMethod);
				checkoutStatement.setString("paymentNumber", paymentNumber);
				checkoutStatement.setBigDecimal("amountReceived", amountReceived);
				checkoutStatement.setString("comments", comments);
				checkoutStatement.setInt("createdBy", currentUserId);
				checkoutStatement.registerOutParameter("transactionId", java.sql.Types.NUMERIC);
				checkoutStatement.execute();

				transactionId = checkoutStatement.getLong("transactionId");

				for (Long feeId: feeIds) { // Mark fees as paid.
					CallableStatement feeStatement = DBUtils.prepareProcedure(connection, "payFee", feeId, transactionId, currentUserId);
					feeStatement.execute();
					// Updating TransactionComments
					DBUtils.simpleUpdateQuery(connection, " UPDATE Fees SET TransactionComments=:comments where ID=" + feeId, queryParams);

				}
				for (Long feeId: feeIds) {
				 Long currentStatusId = DBUtils.selectQuery(connection, "select MF.FormStatusId as formStatusId from Fees F Inner Join MasterForms MF on F.FormGuid=MF.FormGuid Where F.ID="+feeId, null).get(0).getLong("formStatusId");
					// Check for AdvanceOnZeroBalance	
				 Long newStatusId=checkAdvanceOnZeroBalance(feeId, connection, comments, request);
					// Record Payment history on form
					recordFeeHistoryOnForm(feeId, transactionId, newStatusId, currentStatusId, connection);
				}

			}

			if (transactionSuccess) {
				connection.commit();
			} else {
				connection.rollback();
				logger.info("There was a problem processing payment and the transaction has been rolled back.");
				logger.info("paymentNumber: " + paymentNumber);
				logger.info("currentUserId: " + currentUserId);
				throw new Exception("There was a problem processing payment and the transaction has been rolled back.");
			}

			if (transactionId != null && transactionSuccess) {
				byte[] receiptBytes = createReceiptPdf(transactionId);
				Cx2DataAccess db = new Cx2DataAccess();
				PaymentReceipt paymentReceipt = null;
				ReceiptPdf receiptPdf = null;
				String userEmail = null;
				paymentReceipt = db.getPaymentReceipt(transactionId);
				userEmail = paymentReceipt.getUserEmail();
				receiptPdf = new ReceiptPdf(paymentReceipt);
				receiptBytes = receiptPdf.fileBytesOutput;
				System.out.println("receiptBytes.length:" + receiptBytes.length);
				sendReceipt(userEmail, receiptBytes);
				logger.info("CX2 payment receipt sent to " + userEmail);
			}

		} catch(Exception e) {
			connection.rollback();
			throw e;
		} finally {
			connection.close();
		}
	}

	public byte[] createReceiptPdf(Long transactionId) {
		Cx2DataAccess db = new Cx2DataAccess();
		PaymentReceipt paymentReceipt = null;
		ReceiptPdf receiptPdf = null;
		paymentReceipt = db.getPaymentReceipt(transactionId);
		receiptPdf = new ReceiptPdf(paymentReceipt);
		byte[] fileBytes = receiptPdf.fileBytesOutput;
		return fileBytes;
	}

	public DownloadResponse downloadReceiptPdf(Long transactionId) {
		byte[] fileBytes = createReceiptPdf(transactionId);
		ByteArrayInputStream downloadBais = new ByteArrayInputStream(fileBytes);
		DownloadResponse dr = new DownloadResponse();
		dr.setContents(downloadBais);
		dr.setContentType("application/pdf");
		dr.setFileName("receipt" + transactionId.toString() + ".pdf");
		return dr;
	}

	private Boolean chargeCreditCard(BigDecimal amount, String description, String stripeToken) {
		Boolean returnSuccess = false;
		Charge charge = null;
		Map < String,
		Object > params = null;
		Long amountInCents = amount.longValue() * 100;

		params = new HashMap < String,
		Object > ();
		params.put("amount", amountInCents);
		params.put("currency", "usd");
		params.put("description", description); // can this description make a useful receipt from Stripe?
		params.put("source", stripeToken);
		try {

			String status = null;
			ChargeOutcome outcome = null;
			String outcomeType = null;
			charge = Charge.create(params);
			status = charge.getStatus();
			outcome = charge.getOutcome();
			outcomeType = outcome.getType();
			System.out.println("charge.getId(): " + charge.getId());
			System.out.println("charge.getOutcome(): " + charge.getOutcome());
			System.out.println("outcomeType: " + outcomeType);

			// outcomeType = "declined"; // simulate a decline, even though Stripe should handle it
			if (outcomeType.equals("authorized")) {
				System.out.println("outcomeType authorized");
				BigDecimal decimalAmount = new BigDecimal(charge.getAmount() / 100);
				System.out.println("decimalAmount: " + decimalAmount.toString());
				returnSuccess = true;
			} else {
				// TODO: how will user know what the problem is? I need to pass an output value
				System.out.println("outcomeType NOT authorized");
				returnSuccess = false;
			}
		} catch(AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(InvalidRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(CardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnSuccess;
	}

	public void sendReceipt(String recipientEmail, byte[] receiptBytes) throws MessagingException {
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.enabled", "true");
		props.put("mail.imap.ssl.enabled", "true");
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(RESET_NOTIFICATION_MAIL_ID));
		InternetAddress recipientAddress;
		recipientAddress = new InternetAddress(recipientEmail);
		message.setRecipient(Message.RecipientType.TO, recipientAddress);
		StringBuilder emailContent = new StringBuilder();
		emailContent.append("Here is your receipt.");
		emailContent.append("<br /><br />");
		Multipart messageContents = new MimeMultipart();
		MimeBodyPart messageBody = new MimeBodyPart();
		messageBody.setContent(emailContent.toString(), "text/html");
		messageContents.addBodyPart(messageBody);
		String filename = "civicxpress-receipt.pdf";
		ByteArrayDataSource fileDS = new ByteArrayDataSource(receiptBytes, "application/pdf");
		MimeBodyPart letterAttachment = new MimeBodyPart();
		letterAttachment.setDataHandler(new DataHandler(fileDS));
		letterAttachment.setFileName(filename);
		messageContents.addBodyPart(letterAttachment);
		message.setSubject("CivicXpress receipt");
		message.setContent(messageContents);
		Transport tr = session.getTransport("smtp");
		tr.connect("smtp.gmail.com", 587, RESET_NOTIFICATION_MAIL_ID, RESET_NOTIFICATION_MAIL_PASSWORD);
		message.saveChanges();
		tr.sendMessage(message, message.getAllRecipients());
		tr.close();

	}

	private Long checkAdvanceOnZeroBalance(Long feeId, Connection connection, String comments, HttpServletRequest request) throws SQLException,
	MessagingException {
		DBQueryParams params = new DBQueryParams();

		String formGuid = "";
		formGuid = DBUtils.selectQuery(connection, "SELECT FormGuid from Fees where ID=" + feeId, params).get(0).getString("FormGuid");
		params.addString("formGuid", formGuid);

		Long currentStatusId = DBUtils.selectQuery(connection, "SELECT FormStatusId as formStatusId from MasterForms where FormGUID=:formGuid", params).get(0).getLong("formStatusId");

		// Check if form has Zero balance
		BigDecimal balanceDue = DBUtils.selectQuery(connection, "SELECT BalanceDue as balanceDue from MasterForms where FormGUID=:formGuid", params).get(0).getBigDecimal("balanceDue");
		BigDecimal zero = new BigDecimal("0");
		if (balanceDue.compareTo(zero) == 0) {
			DBRow feeData = DBUtils.selectOne(connection, "SELECT FS.StatusToBeOnForAdvanceOnZero as newStatusId,FS.AdvanceOnZero as advanceOnZero from MasterForms MF, FormStatuses FS  where  MF.FormGUID=:formGuid and  MF.FormStatusId = FS.ID  ", params);
			Boolean advanceOnZero = feeData.getBoolean("advanceOnZero");
			if (advanceOnZero) {
				Long newStatusId = feeData.getLong("newStatusId");
				String formLink = null;
				String requestUrl = request.getRequestURL().toString();
				if (requestUrl.contains("civic-dev.com")) {
					formLink = "http://civic-dev.com/#/Forms?FormGUID=" + formGuid;
				} else {
					formLink = "https://www.wavemakeronline.com" + request.getContextPath() + "/#/Forms?FormGUID=" + formGuid;
				}
				comments = "Form advanced to next status on zero balance.";
				formService.setOnZeroFormStatus(formGuid, newStatusId, comments, formLink, connection);
				return newStatusId;
			}
			else {
				return currentStatusId;
			}
		} else {
			return currentStatusId;
		}

	}

	private void recordFeeHistoryOnForm(Long feeId, Long transactionId, Long newStatusId, Long oldStatusId, Connection connection) throws SQLException {
		DBQueryParams params = new DBQueryParams();
		DBRow feeData = DBUtils.selectOne(connection, "SELECT Amount, FeeType, FormGuid from Fees where ID=" + feeId, params);
		BigDecimal amount = feeData.getBigDecimal("Amount");
		String feeType = feeData.getString("FeeType");
		String formGuid = feeData.getString("FormGuid");
		Long createdBy = Long.parseLong(securityService.getUserId());
		String comments = "The " + feeType + " fee $" + amount + " was paid in Transaction " + transactionId;
		params.addString("formGuid", formGuid);
		params.addLong("oldStatusId", oldStatusId);
		params.addLong("newStatusId", newStatusId);
		params.addString("comments", comments);
		params.addLong("createdBy", createdBy);
		DBUtils.simpleUpdateQuery(connection, "INSERT INTO FormHistory (FormGUID,NewStatusId,OldStatusId,Comments,CreatedBy,CreatedTime) " + "VALUES (:formGuid,:newStatusId,:oldStatusId,:comments,:createdBy,SYSUTCDATETIME())", params);
	}

}