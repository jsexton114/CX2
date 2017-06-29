/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class CountUnReadMessagesByUserResponse implements Serializable {


    @ColumnAlias("unReadMessagesCount")
    private Integer unReadMessagesCount;

    public Integer getUnReadMessagesCount() {
        return this.unReadMessagesCount;
    }

    public void setUnReadMessagesCount(Integer unReadMessagesCount) {
        this.unReadMessagesCount = unReadMessagesCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountUnReadMessagesByUserResponse)) return false;
        final CountUnReadMessagesByUserResponse countUnReadMessagesByUserResponse = (CountUnReadMessagesByUserResponse) o;
        return Objects.equals(getUnReadMessagesCount(), countUnReadMessagesByUserResponse.getUnReadMessagesCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUnReadMessagesCount());
    }
}
