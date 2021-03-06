package com.civicxpress.letters;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class GlobalInspectionInfo {

    private Long inspectionDesignId;
    private String inspectionGuid;
    private String formGuid;
    private String inspectionType;
    private String tableName;
    private byte[] municipalityLogo;
    private String municipalityName;
    private Address municipalityAddress;
    private String municipalityPhone;
    private String municipalityEmail;
    private String vendorCompanyName;
    private Address vendorAddress;
    private BigDecimal totalFees;
    private BigDecimal balanceDue;
    private String formTitle;
    private Date expiresDate;
    private Date issuedDate;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerPhone;
    private Address ownerAddress;
    private String tenantFirstName;
    private String tenantLastName;
    private String locationParcel;
    private String locationLot;
    private Address locationAddress;
    private double locationLatitude;
    private double locationLongitude;
    private String subdivision;
    private String userSubmitted;
    private Date dateGenerated;
    private String currentStatus;
    private String currentStatusUser;
    private Date currentStatusDate;
    private String currentStatusYear;
    private HashMap<String, byte[]> additionalImages;
    
    public Long getInspectionDesignId() {
		return inspectionDesignId;
	}

	public void setInspectionDesignId(Long inspectionDesignId) {
		this.inspectionDesignId = inspectionDesignId;
	}

	public String getInspectionGuid() {
		return inspectionGuid;
	}

	public void setInspectionGuid(String inspectionGuid) {
		this.inspectionGuid = inspectionGuid;
	}

	public String getFormGuid() {
		return formGuid;
	}

	public void setFormGuid(String formGuid) {
		this.formGuid = formGuid;
	}

	public String getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(String inspectionType) {
		this.inspectionType = inspectionType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public byte[] getMunicipalityLogo() {
        return municipalityLogo;
    }

    public void setMunicipalityLogo(byte[] municipalityLogo) {
        this.municipalityLogo = municipalityLogo;
    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public Address getMunicipalityAddress() {
        return municipalityAddress;
    }

    public void setMunicipalityAddress(Address municipalityAddress) {
        this.municipalityAddress = municipalityAddress;
    }

    public String getMunicipalityPhone() {
        return municipalityPhone;
    }

    public void setMunicipalityPhone(String municipalityPhone) {
        this.municipalityPhone = municipalityPhone;
    }

    public void setMunicipalityEmail(String municipalityEmail) {
        this.municipalityEmail = municipalityEmail;
    }

    public String getMunicipalityEmail() {
        return municipalityEmail;
    }

    public String getVendorCompanyName() {
        return vendorCompanyName;
    }

    public void setVendorCompanyName(String vendorCompanyName) {
        this.vendorCompanyName = vendorCompanyName;
    }

    public Address getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(Address vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public BigDecimal getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(BigDecimal totalFees) {
        this.totalFees = totalFees;
    }

    public BigDecimal getBalanceDue() {
        return balanceDue;
    }

    public void setBalanceDue(BigDecimal balanceDue) {
        this.balanceDue = balanceDue;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }
    
    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }
    
    public Address getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(Address ownerAddress) {
        this.ownerAddress = ownerAddress;
    }
    
    public String getTenantFirstName() {
        return tenantFirstName;
    }

    public void setTenantFirstName(String tenantFirstName) {
        this.tenantFirstName = tenantFirstName;
    }

    public String getTenantLastName() {
        return tenantLastName;
    }

    public void setTenantLastName(String tenantLastName) {
        this.tenantLastName = tenantLastName;
    }

    public String getLocationParcel() {
        return locationParcel;
    }

    public void setLocationParcel(String locationParcel) {
        this.locationParcel = locationParcel;
    }

    public String getLocationLot() {
        return locationLot;
    }

    public void setLocationLot(String locationLot) {
        this.locationLot = locationLot;
    }

    public Address getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(Address locationAddress) {
        this.locationAddress = locationAddress;
    }

    public double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public String getSubdivision() {
        return this.subdivision;
    }
    
    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getUserSubmitted() {
        return this.userSubmitted;
    }
    
    public void setUserSubmitted(String userSubmitted) {
        this.userSubmitted = userSubmitted;    
    }
    
    public Date getDateGenerated() {
        return this.dateGenerated;        
    }
    
    public void setDateGenerated(Date dateGenerated) {
        this.dateGenerated = dateGenerated;
    }

    public String getCurrentStatus() {
        return this.currentStatus;
    }
    
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getCurrentStatusUser() {
        return this.currentStatusUser;
    }
    
    public void setCurrentStatusUser(String currentStatusUser) {
        this.currentStatusUser = currentStatusUser;        
    }
    
    public Date getCurrentStatusDate() {
        return this.currentStatusDate;
    }
    
    public void setCurrentStatusDate(Date currentStatusDate) {
        this.currentStatusDate = currentStatusDate;
    }
    
    public String getCurrentStatusYear() {
        return this.currentStatusYear;
    }
    
    public void setCurrentStatusYear(String currentStatusYear) {
        this.currentStatusYear = currentStatusYear;
    }
    
    public HashMap<String, byte[]> getAdditionalImages() {
        return this.additionalImages;
    }

    public void setAdditionalImages(HashMap<String, byte[]> additionalImages) {
        this.additionalImages = additionalImages;
    }

}
