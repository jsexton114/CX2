package com.civicxpress.letters;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.naming.ConfigurationException;
import javax.security.auth.login.Configuration;

import com.civicxpress.letters.PdfDocument.TextJustification;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	public static final String LOREM_IPSUM_LONG = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

	public static void main(String[] args) {
//		testGetLetterTemplate();
//		howLetterServiceWillCallIt();
	}

//	private static void howLetterServiceWillCallIt() {
//		int letterTemplateId = 21;
//		long formTypeId = 107;;
//		String formGuid = "7815E95D-ACFF-E611-80C9-0CC47A46DD63";
//		SectionalTemplatePdf lt = null;
//		DatabaseConnectionInfo dbInfo = new DatabaseConnectionInfo();
////		dbInfo.readFromWebContext();
////		Cx2DataAccess db = new Cx2DataAccess(dbInfo);
////		Cx2DataAccess db = new Cx2DataAccess();
//		lt = db.getLetterTemplate(letterTemplateId);
////        GlobalFormInfo globalFormInfo = db.getGlobalFormInfo(formTypeId, formGuid);
////        Map<String, String> textTokens = LetterTemplate.getTextTokenValues(formTypeId, formGuid);
//        byte[] fileBytes = lt.createLetter(globalFormInfo, textTokens);
//        writeToFile(formGuid, fileBytes);
//        
//	}
//
////    public DownloadResponse createLetterFromDatabaseTemplate(Long formTypeId, String formGuid, int letterTemplateId) {
////		SectionalTemplatePdf lt = null;
////		DatabaseConnectionInfo dbInfo = new DatabaseConnectionInfo();
////		dbInfo.readFromWebContext();
////		Cx2DataAccess db = new Cx2DataAccess(dbInfo);
////		lt = db.getLetterTemplate(letterTemplateId);
////        GlobalFormInfo globalFormInfo = db.getGlobalFormInfo(formTypeId, formGuid);
////        Map<String, String> textTokens = LetterTemplate.getTextTokenValues(formTypeId, formGuid);
////        byte[] fileBytes = lt.createLetter(globalFormInfo, textTokens);
////        ByteArrayInputStream downloadBais = new ByteArrayInputStream(fileBytes);
////        DownloadResponse dr = new DownloadResponse();
////        dr.setContents(downloadBais);
////        dr.setContentType("application/pdf");
////        dr.setFileName(formGuid + ".pdf");
////        return dr;
////    }
//
//	private static void testGetLetterTemplate() {
//		SectionalTemplatePdf lt = null;
//		Cx2DataAccess db = new Cx2DataAccess();
//		lt = db.getLetterTemplate(21);
//		System.out.println(lt.getTitle() + " rows: " + lt.getAllRows().size());
//	}
//	
//	private static void testCleanSectionalLetterCode2() {
//		Long formTypeId = 107l;
//		String formGuid = "7815E95D-ACFF-E611-80C9-0CC47A46DD63";
//		SectionalTemplatePdf letterTemplate = new SectionalTemplatePdf();
//        Cx2DataAccess db = new Cx2DataAccess();
//        GlobalFormInfo globalFormInfo = db.getGlobalFormInfo(formTypeId, formGuid);
//        Map<String, String> textTokens = LetterTemplate.getTextTokenValues(formTypeId, formGuid);
//		File file = null;
//		OutputStream outStream = null;
//		byte[] fileBytes;
//		final TextJustification centered = TextJustification.Centered;
//		final TextJustification left = TextJustification.Left;
//		List<TemplateSection> headerSections = null;
//		List<TemplateSection> currentRowSections = null;
//		List<TemplateSection> footerSections = null;
//
//		letterTemplate.setUpDefaultSections();
//		headerSections = letterTemplate.getAllRows().get(0).getSections();
//		headerSections.get(0).setText("HEADER LEFT", centered, true);
//		headerSections.get(1).setText("HEADER CENTER", centered, true);
//		headerSections.get(2).setText("HEADER LEFT", centered, true);
//		currentRowSections = letterTemplate.getAllRows().get(1).getSections(); 
//		currentRowSections.get(0).setText("ROW 1 " + LOREM_IPSUM, left, true);
//		currentRowSections = letterTemplate.getAllRows().get(2).getSections();
//		currentRowSections.get(0).setText("ROW2", left, true);
//		currentRowSections.get(1).setText(LOREM_IPSUM_LONG, left);
////		currentRowSections = letterTemplate.getBodyRows().get(3).getSections();
////		currentRowSections.get(0).setText(LOREM_IPSUM, left);
////		currentRowSections.get(1).setText("ROW3", left, true);
//		currentRowSections = letterTemplate.getAllRows().get(4).getSections();
//		currentRowSections.get(0).setText("ROW4 " + LOREM_IPSUM, left, true);
//		currentRowSections = letterTemplate.getAllRows().get(5).getSections();
//		currentRowSections.get(0).setText("ROW5\n[ContractorName]", left, true);
//		currentRowSections.get(1).setText(LOREM_IPSUM, left);
//		currentRowSections = letterTemplate.getAllRows().get(6).getSections();
//		currentRowSections.get(0).setText(LOREM_IPSUM, left);
//		currentRowSections.get(1).setText("ROW6", left, true);
//		currentRowSections = letterTemplate.getAllRows().get(7).getSections();
//		currentRowSections.get(0).setText("ROW7 " + LOREM_IPSUM, left);
//		footerSections = letterTemplate.getAllRows().get(8).getSections();
//		footerSections.get(0).setText("FOOTER LEFT", centered);
//		footerSections.get(1).setText("FOOTER CENTER", centered);
//		footerSections.get(2).setText("FOOTER LEFT", centered);
//
//		fileBytes = letterTemplate.createLetter(globalFormInfo, textTokens);
//		writeToFile(formGuid, fileBytes);
//	}
//
//	public static void writeToFile(String title, byte[] fileBytes) {
//		File file;
//		OutputStream outStream;
//		String filePath = null;
//		try {
//			file = File.createTempFile(title, ".pdf");
//			// file.deleteOnExit();
//			outStream = new FileOutputStream(file);
//			outStream.write(fileBytes);
//			filePath = file.getPath();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("filePath: " + filePath);
//	}
//
//	private static void testCleanSectionalLetterCode() {
//		String bodyTopLeftTitleTemplate = "LOT NO.";
//		String bodyTopLeftCustomTextTemplate = "12";
//		String bodyTopRightCustomTextTemplate = "Permit Type: \n\nContractor Name: [ContractorName]";
//		String bodyBottomTitleTemplate = "OPEN BURNING PROHIBITED";
//		String bodyBottomCustomTextTemplate = "All construction shall comply with applicable codes and zoning adopted by the City of Fishers. Reinspection fees will be charged for all inspections that have failed or are not ready when requested.\n\nIssued by Todd Suchy, Building Inspector";
//		String bodyFooterCustomText = "THIS PERMIT MUST BE POSTED WHERE IT IS\nVISIBLE FROM THE STREET";
//		File file = null;
//		OutputStream outStream = null;
//		String filePath = null;
//		byte[] fileBytes;
////		fileBytes = SectionalTemplatePdf.createLetter(107l, "7815E95D-ACFF-E611-80C9-0CC47A46DD63",
////				bodyTopLeftTitleTemplate, bodyTopLeftCustomTextTemplate, bodyTopRightCustomTextTemplate,
////				bodyBottomTitleTemplate, bodyBottomCustomTextTemplate, bodyFooterCustomText, false);
//
//		try {
//			file = File.createTempFile("test", ".pdf");
//			// file.deleteOnExit();
//			outStream = new FileOutputStream(file);
////			outStream.write(fileBytes);
//			filePath = file.getPath();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("filePath: " + filePath);
//	}
//
//	public static void testGetWebXmlValue() {
//		String connectionString = null;
//		connectionString = getDatabaseConnectionString();
//		System.out.println(connectionString);
//	}
//
//	private static String getDatabaseConnectionString() {
//		Context ctx = null;
//		String connectionString = null;
//		try {
//			ctx = new InitialContext();
//			Context env = (Context) ctx.lookup("java:comp/env");
//			connectionString = (String) env.lookup("dbConnectionString");
//
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//		return connectionString;
//	}
//
//	private static void testApacheCommonsConfiguration() {
//		/*
//		 * Configurations configs = new Configurations(); try { Configuration
//		 * config = configs.properties(new File("config.properties")); // access
//		 * configuration properties } catch (ConfigurationException cex) { //
//		 * Something went wrong }
//		 */
//	}
//
//	private static void testFindLineSeparatorRegex() {
//		final String lineSeparator = System.lineSeparator(); // "testxyz";
//		final String LINE_BREAK_PATTERN = ".*?(([\\h-]+)|(" + lineSeparator + "?))";
//		String text = "One line of text." + lineSeparator + lineSeparator + "Another line of text.";
//		Pattern pattern = Pattern.compile(LINE_BREAK_PATTERN);
//		Matcher matcher = pattern.matcher(text + " ");
//		while (matcher.find()) {
//			String word = matcher.group();
//			boolean doesWordEndWithSeparator = word.endsWith(lineSeparator);
//			// word = word.replaceAll(lineSeparator, "*");
//			System.out.print(word);
//			// System.out.println("word: " + word + " doesWordEndWithSeparator:
//			// " + doesWordEndWithSeparator);
//		}
//	}
//
//	public static void testAddressMethods() {
//		GlobalFormInfo globalFormInfo = null;
//		Cx2DataAccess db = new Cx2DataAccess();
//		globalFormInfo = db.getGlobalFormInfo(107l, "7815E95D-ACFF-E611-80C9-0CC47A46DD63");
//		Address municipalityAddress = globalFormInfo.getMunicipalityAddress();
//		System.out.println("getFormattedSingleLineAddress: " + municipalityAddress.getFormattedSingleLineAddress());
//		System.out
//				.println("getFormattedMultipleLinesAddress: " + municipalityAddress.getFormattedMultipleLinesAddress());
//		System.out.println("getFormattedHtmlAddress: " + municipalityAddress.getFormattedHtmlAddress());
//	}
//
//
//	private static void testCreateLetterFromTokenValuesJson() {
//		String json = null;
//		String letter = null;
//		String html = "<h1>[ContractorName]</h1>";
//		LetterTemplate lt = new LetterTemplate(html);
//		json = LetterTemplate.getTokenValuesJson(107l, "7815E95D-ACFF-E611-80C9-0CC47A46DD63");
//		System.out.println(json);
//		letter = lt.createLetterFromTokenValuesJson(json);
//		System.out.println(letter);
//	}
//
//	private static void testGetTokenValuesJson() {
//		String json = null;
//		json = LetterTemplate.getTokenValuesJson(107l, "7815E95D-ACFF-E611-80C9-0CC47A46DD63");
//		System.out.println(json);
//	}
//
//	private static String testGetFieldBySwitch(String field) {
//		Cx2DataAccess db = new Cx2DataAccess();
//		GlobalFormInfo globalFormInfo = db.getGlobalFormInfo(107l, "7815E95D-ACFF-E611-80C9-0CC47A46DD63");
//		String returnValue = null;
//		switch (field) {
//		case "MunicipalityPhone":
//			returnValue = globalFormInfo.getMunicipalityPhone();
//		}
//		return returnValue;
//	}
//
//	private static void testGetReflectionField() {
//		Class c = GlobalFormInfo.class;
//		Method[] methods = c.getDeclaredMethods();
//		try {
//			Method getter = c.getMethod("get" + "MunicipalityPhone");
//			// String municipalityPhone = getter.invoke( );
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	private static void testGetGlobalFormInfo() {
//		GlobalFormInfo globalFormInfo = null;
//		Cx2DataAccess db = new Cx2DataAccess();
//		globalFormInfo = db.getGlobalFormInfo(107l, "7815E95D-ACFF-E611-80C9-0CC47A46DD63");
//		String formTitle = globalFormInfo.getFormTitle();
//		String municipalityName = globalFormInfo.getMunicipalityName();
//		Address municipalityAddress = globalFormInfo.getMunicipalityAddress();
//		System.out.format("%s, %s", formTitle, municipalityName);
//		System.out.println();
//		System.out.format("%s, %s, %s, %s, %s", municipalityAddress.getAddressLine1(),
//				municipalityAddress.getAddressLine2(), municipalityAddress.getCity(), municipalityAddress.getState(),
//				municipalityAddress.getPostalCode());
//	}
//
//	private static void testGetFormDataValues() {
//		DynamicForm form = null;
//		Cx2DataAccess db = new Cx2DataAccess();
//		form = db.getFormData(107l, "7815E95D-ACFF-E611-80C9-0CC47A46DD63");
//		Map<String, Object> formDataValues = form.getFormDataValues();
//		System.out.println("formDataValues.size(): " + formDataValues.size());
//	}
//
//	private static void testGetFormInfo() {
//		DynamicForm form = null;
//		Cx2DataAccess db = new Cx2DataAccess();
//		form = db.getFormInfo(107l, "7815E95D-ACFF-E611-80C9-0CC47A46DD63");
//		String formTitle = form.getFormTitle();
//		String creatorFullName = form.getCreatorFullName();
//		System.out.format("%s, %s", formTitle, creatorFullName);
//	}
//
//	private static void testGetFormType() {
//		DynamicFormType formType = null;
//		Cx2DataAccess db = new Cx2DataAccess();
//		formType = db.getFormType(107l);
//		String formType1 = formType.getFormType();
//		String formTableName = formType.getFormTableName();
//		Long municipalityId = formType.getMunicipalityId();
//		System.out.format("%s, %s, %d", formType1, formTableName, municipalityId);
//	}
//
//	private static void testGetAvailableTokens() {
//		List<String> tokens = null;
//		tokens = LetterTemplate.getAvailableTokens(107l);
//		System.out.println("tokens.size(): " + tokens.size());
//	}
//
//	private static void testGetTokenValues() {
//		Map<String, String> tokens = LetterTemplate.getTextTokenValues(107l, "7815E95D-ACFF-E611-80C9-0CC47A46DD63");
//		System.out.println("tokens.size(): " + tokens.size());
//		Iterator tokenIterator = tokens.entrySet().iterator();
//		while (tokenIterator.hasNext()) {
//			Entry tokenEntry = (Entry) tokenIterator.next();
//			System.out.println(tokenEntry.getKey() + ": " + tokenEntry.getValue());
//		}
//	}
//
//	private static void testDatabase() {
//		Cx2DataAccess db = new Cx2DataAccess();
//		List<String> fieldNames = db.getFormTypeFieldNames(107);
//		System.out.println("fieldNames.size(): " + fieldNames.size());
//		for (String fieldName : fieldNames) {
//			System.out.println("[" + fieldName + "]");
//		}
//	}
//
//	public static void testLetterTemplate() {
//		String letterTemplate = "<h1>[Title]</h1><h2>WaveMaker's \"Rich Text\" editor looks an awful lot like HTML</h2>"
//				+ "<p>Owner: [Owner]</p>" + "<p>Time of outbreak start: [TimeOfOutbreakStart]</p>"
//				+ "<p>Contractor name: [ContractorName]</p>" + "<p>Contractor address:<br/> [ContractorAddress]</p>"
//				+ "<p>The editor has H1-H6 tags, a P tag, a PRE tag, <b>bold</b>, <i>italic</i>, bullets, etc. [This isn't a token.] Try different settings and see what the output looks like. If <i>letter templates</i> produced by CX2 are written in this editor, then the Letters project should assume HTML content is allowed in the letter template.&nbsp;</p>"
//				+ "<p style=\"text-align: right;\">In this case, the PDF generation by the Letters project could use an HTML-to-PDF conversion rather than writing directly to a PDF as is done with Apache PDFBox for document signatures. This paragraph is right-justified.</p>"
//				+ "<p style=\"text-align: left;\">Only now have I noticed the \"Toggle html / Rich Text\" toolbar button. In this case, will the Letters project need to support both? In many ways I'd like to keep the letters simple by using plain text plus a few tokens, and avoid both HTML and Rich Text conversions to PDF.</p>"
//				+ "<p style=\"text-align: left;\">So the toggle button seem to toggle between an HTML plain text editing and display view. So it's possible this editor isn't using Rich Text after all.</p>"
//				+ "<p>This is admittedly, some clean-looking HTML. What happens if I put some HTML in here that isn't on the toolbar?</p>"
//				+ "[hr]"
//				+ "<p>For instance, there should be a horizontal line above this paragraph, but it's not showing in WM. Maybe CSS is killing it, and that's OK. Check with F12. Yep, the HR is there just fine. So how should a user enter a check box?</p>"
//				+ "<p>[checkbox]</p>"
//				+ "<p>That is one glorious checkbox. So the token menu could always add a few amenities like check boxes, and plug the appropriate HTML into the editor as needed. Is the user going to want tables or columns? Let's say \"no\" to this at least for version one. However, a clever user could always do this in HTML anyway. So it looks like the PdfUtilities class will need an HTML-to-PDF converter. And the Letters project need only replace the token values.&nbsp;</p>"
//				+ "<p style=\"text-align: left;\">[ContractorName]</p>";
//		Map<String, String> tokens = LetterTemplate.getTextTokenValues(107l, "7815E95D-ACFF-E611-80C9-0CC47A46DD63");
//		tokens.put("Title", "The Title");
//		tokens.put("checkbox", "<input type=\"checkbox\">");
//		tokens.put("hr", "<hr/>");
//		LetterTemplate lt = new LetterTemplate(letterTemplate);
//		lt.createLetter(tokens);
//		System.out.println("lt.getOutput: " + lt.getOutput());
//	}

}
