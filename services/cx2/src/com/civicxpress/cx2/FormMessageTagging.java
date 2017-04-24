/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FormMessageTagging generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`FormMessageTagging`")
public class FormMessageTagging implements Serializable {

    private Integer id;
    private Integer formMessageId;
    private Integer taggedPersonId;
    private Boolean messageRead;
    private Users users;
    private FormMessages formMessages;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`FormMessageId`", nullable = true, scale = 0, precision = 10)
    public Integer getFormMessageId() {
        return this.formMessageId;
    }

    public void setFormMessageId(Integer formMessageId) {
        this.formMessageId = formMessageId;
    }

    @Column(name = "`TaggedPersonId`", nullable = true, scale = 0, precision = 10)
    public Integer getTaggedPersonId() {
        return this.taggedPersonId;
    }

    public void setTaggedPersonId(Integer taggedPersonId) {
        this.taggedPersonId = taggedPersonId;
    }

    @Column(name = "`MessageRead`", nullable = true)
    public Boolean getMessageRead() {
        return this.messageRead;
    }

    public void setMessageRead(Boolean messageRead) {
        this.messageRead = messageRead;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`TaggedPersonId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        if(users != null) {
            this.taggedPersonId = users.getId();
        }

        this.users = users;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`FormMessageId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public FormMessages getFormMessages() {
        return this.formMessages;
    }

    public void setFormMessages(FormMessages formMessages) {
        if(formMessages != null) {
            this.formMessageId = formMessages.getId();
        }

        this.formMessages = formMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormMessageTagging)) return false;
        final FormMessageTagging formMessageTagging = (FormMessageTagging) o;
        return Objects.equals(getId(), formMessageTagging.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

