/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * Users generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`Users`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"`Email`"})})
public class Users implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String postalCode;
    private String phoneNumber;
    private String communicationFrequency;
    private Boolean active;
    @JsonProperty(access = Access.READ_ONLY)
    private byte[] photo;
    private Boolean banned;
    private String password;
    private Integer stateId;
    private String country;
    private String fullName;
    private States states;
    private List<Fees> feeses = new ArrayList<>();
    private List<FormHistory> formHistories = new ArrayList<>();
    private List<FormMessages> formMessageses = new ArrayList<>();
    private List<FormMessageTagging> formMessageTaggings = new ArrayList<>();
    private List<Gis2forms> gis2formses = new ArrayList<>();
    private List<MasterForms> masterFormses = new ArrayList<>();
    private List<ProjectForms> projectFormses = new ArrayList<>();
    private List<ProjectGisrecords> projectGisrecordses = new ArrayList<>();
    private List<Projects> projectsesForCreatedBy = new ArrayList<>();
    private List<Projects> projectsesForModifiedBy = new ArrayList<>();
    private List<ProjectSharedWith> projectSharedWithsForProjectSharedBy = new ArrayList<>();
    private List<ProjectSharedWith> projectSharedWithsForProjectSharedWithUser = new ArrayList<>();
    private List<ProjectTasks> projectTaskses = new ArrayList<>();
    private List<Roles> roleses = new ArrayList<>();
    private List<SharedWith> sharedWithsForCreatedBy = new ArrayList<>();
    private List<SharedWith> sharedWithsForSharedWithUser = new ArrayList<>();
    private List<MunicipalityGroupMembers> municipalityGroupMemberses = new ArrayList<>();
    private List<UserPasswordResetTokens> userPasswordResetTokenses = new ArrayList<>();
    private List<UserSubscriptions> userSubscriptionses = new ArrayList<>();
    private List<VendorAdmins> vendorAdminses = new ArrayList<>();
    private List<VendorUsers> vendorUserses = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`FirstName`", nullable = true, length = 255)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "`LastName`", nullable = true, length = 255)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "`Email`", nullable = false, length = 255)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "`Address1`", nullable = true, length = 255)
    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Column(name = "`Address2`", nullable = true, length = 255)
    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Column(name = "`City`", nullable = true, length = 255)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "`PostalCode`", nullable = true, length = 255)
    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name = "`PhoneNumber`", nullable = true, length = 255)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "`CommunicationFrequency`", nullable = true, length = 255)
    public String getCommunicationFrequency() {
        return this.communicationFrequency;
    }

    public void setCommunicationFrequency(String communicationFrequency) {
        this.communicationFrequency = communicationFrequency;
    }

    @Column(name = "`Active`", nullable = true)
    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Column(name = "`Photo`", nullable = true)
    public byte[] getPhoto() {
        return this.photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Column(name = "`Banned`", nullable = true)
    public Boolean getBanned() {
        return this.banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    @Column(name = "`Password`", nullable = true, length = 255)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "`StateId`", nullable = true, scale = 0, precision = 10)
    public Integer getStateId() {
        return this.stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    @Column(name = "`Country`", nullable = true, length = 255)
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "`FullName`", nullable = false, insertable = false, updatable = false, length = 511)
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`StateId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public States getStates() {
        return this.states;
    }

    public void setStates(States states) {
        if(states != null) {
            this.stateId = states.getId();
        }

        this.states = states;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<Fees> getFeeses() {
        return this.feeses;
    }

    public void setFeeses(List<Fees> feeses) {
        this.feeses = feeses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<FormHistory> getFormHistories() {
        return this.formHistories;
    }

    public void setFormHistories(List<FormHistory> formHistories) {
        this.formHistories = formHistories;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<FormMessages> getFormMessageses() {
        return this.formMessageses;
    }

    public void setFormMessageses(List<FormMessages> formMessageses) {
        this.formMessageses = formMessageses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<FormMessageTagging> getFormMessageTaggings() {
        return this.formMessageTaggings;
    }

    public void setFormMessageTaggings(List<FormMessageTagging> formMessageTaggings) {
        this.formMessageTaggings = formMessageTaggings;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<Gis2forms> getGis2formses() {
        return this.gis2formses;
    }

    public void setGis2formses(List<Gis2forms> gis2formses) {
        this.gis2formses = gis2formses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<MasterForms> getMasterFormses() {
        return this.masterFormses;
    }

    public void setMasterFormses(List<MasterForms> masterFormses) {
        this.masterFormses = masterFormses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<ProjectForms> getProjectFormses() {
        return this.projectFormses;
    }

    public void setProjectFormses(List<ProjectForms> projectFormses) {
        this.projectFormses = projectFormses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<ProjectGisrecords> getProjectGisrecordses() {
        return this.projectGisrecordses;
    }

    public void setProjectGisrecordses(List<ProjectGisrecords> projectGisrecordses) {
        this.projectGisrecordses = projectGisrecordses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "usersByCreatedBy")
    public List<Projects> getProjectsesForCreatedBy() {
        return this.projectsesForCreatedBy;
    }

    public void setProjectsesForCreatedBy(List<Projects> projectsesForCreatedBy) {
        this.projectsesForCreatedBy = projectsesForCreatedBy;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "usersByModifiedBy")
    public List<Projects> getProjectsesForModifiedBy() {
        return this.projectsesForModifiedBy;
    }

    public void setProjectsesForModifiedBy(List<Projects> projectsesForModifiedBy) {
        this.projectsesForModifiedBy = projectsesForModifiedBy;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "usersByProjectSharedBy")
    public List<ProjectSharedWith> getProjectSharedWithsForProjectSharedBy() {
        return this.projectSharedWithsForProjectSharedBy;
    }

    public void setProjectSharedWithsForProjectSharedBy(List<ProjectSharedWith> projectSharedWithsForProjectSharedBy) {
        this.projectSharedWithsForProjectSharedBy = projectSharedWithsForProjectSharedBy;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "usersByProjectSharedWithUser")
    public List<ProjectSharedWith> getProjectSharedWithsForProjectSharedWithUser() {
        return this.projectSharedWithsForProjectSharedWithUser;
    }

    public void setProjectSharedWithsForProjectSharedWithUser(List<ProjectSharedWith> projectSharedWithsForProjectSharedWithUser) {
        this.projectSharedWithsForProjectSharedWithUser = projectSharedWithsForProjectSharedWithUser;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<ProjectTasks> getProjectTaskses() {
        return this.projectTaskses;
    }

    public void setProjectTaskses(List<ProjectTasks> projectTaskses) {
        this.projectTaskses = projectTaskses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<Roles> getRoleses() {
        return this.roleses;
    }

    public void setRoleses(List<Roles> roleses) {
        this.roleses = roleses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "usersByCreatedBy")
    public List<SharedWith> getSharedWithsForCreatedBy() {
        return this.sharedWithsForCreatedBy;
    }

    public void setSharedWithsForCreatedBy(List<SharedWith> sharedWithsForCreatedBy) {
        this.sharedWithsForCreatedBy = sharedWithsForCreatedBy;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "usersBySharedWithUser")
    public List<SharedWith> getSharedWithsForSharedWithUser() {
        return this.sharedWithsForSharedWithUser;
    }

    public void setSharedWithsForSharedWithUser(List<SharedWith> sharedWithsForSharedWithUser) {
        this.sharedWithsForSharedWithUser = sharedWithsForSharedWithUser;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<MunicipalityGroupMembers> getMunicipalityGroupMemberses() {
        return this.municipalityGroupMemberses;
    }

    public void setMunicipalityGroupMemberses(List<MunicipalityGroupMembers> municipalityGroupMemberses) {
        this.municipalityGroupMemberses = municipalityGroupMemberses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<UserPasswordResetTokens> getUserPasswordResetTokenses() {
        return this.userPasswordResetTokenses;
    }

    public void setUserPasswordResetTokenses(List<UserPasswordResetTokens> userPasswordResetTokenses) {
        this.userPasswordResetTokenses = userPasswordResetTokenses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<UserSubscriptions> getUserSubscriptionses() {
        return this.userSubscriptionses;
    }

    public void setUserSubscriptionses(List<UserSubscriptions> userSubscriptionses) {
        this.userSubscriptionses = userSubscriptionses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<VendorAdmins> getVendorAdminses() {
        return this.vendorAdminses;
    }

    public void setVendorAdminses(List<VendorAdmins> vendorAdminses) {
        this.vendorAdminses = vendorAdminses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<VendorUsers> getVendorUserses() {
        return this.vendorUserses;
    }

    public void setVendorUserses(List<VendorUsers> vendorUserses) {
        this.vendorUserses = vendorUserses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        final Users users = (Users) o;
        return Objects.equals(getId(), users.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

