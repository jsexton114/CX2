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
    private List<SfnewResidentialStructure> sfnewResidentialStructures = new ArrayList<>();
    private List<SfnewElectricConnection> sfnewElectricConnections = new ArrayList<>();
    private List<UserPasswordResetTokens> userPasswordResetTokenses = new ArrayList<>();
    private States states;
    private List<Roles> roleses = new ArrayList<>();
    private List<UserSubscriptions> userSubscriptionses = new ArrayList<>();

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<SfnewResidentialStructure> getSfnewResidentialStructures() {
        return this.sfnewResidentialStructures;
    }

    public void setSfnewResidentialStructures(List<SfnewResidentialStructure> sfnewResidentialStructures) {
        this.sfnewResidentialStructures = sfnewResidentialStructures;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<SfnewElectricConnection> getSfnewElectricConnections() {
        return this.sfnewElectricConnections;
    }

    public void setSfnewElectricConnections(List<SfnewElectricConnection> sfnewElectricConnections) {
        this.sfnewElectricConnections = sfnewElectricConnections;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<UserPasswordResetTokens> getUserPasswordResetTokenses() {
        return this.userPasswordResetTokenses;
    }

    public void setUserPasswordResetTokenses(List<UserPasswordResetTokens> userPasswordResetTokenses) {
        this.userPasswordResetTokenses = userPasswordResetTokenses;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<Roles> getRoleses() {
        return this.roleses;
    }

    public void setRoleses(List<Roles> roleses) {
        this.roleses = roleses;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<UserSubscriptions> getUserSubscriptionses() {
        return this.userSubscriptionses;
    }

    public void setUserSubscriptionses(List<UserSubscriptions> userSubscriptionses) {
        this.userSubscriptionses = userSubscriptionses;
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

