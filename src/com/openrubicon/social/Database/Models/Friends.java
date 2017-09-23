package com.openrubicon.social.Database.Models;

import java.util.Date;
import java.util.List;

/**
 * Created by Quinn on 9/23/2017.
 */
public class Friends {

    private int friend_pk;
    private String uuid1;
    private String uuid2;
    private int bff;
    private int status;
    private Date created_on;
    private Date modified_on;
    private Date deleted_on;

    public long getFriend_pk() {
        return friend_pk;
    }
    public void setFriend_pk(int friend_pk) {
        this.friend_pk = friend_pk;
    }
    public String getUuid1() {
        return uuid1;
    }
    public void setUuid1(String uuid1) {
        this.uuid1 = uuid1;
    }
    public String getUuid2() {
        return uuid2;
    }
    public void setUuid2(String uuid2) {
        this.uuid2 = uuid2;
    }
    public int getBff() {
        return bff;
    }
    public void setBff(int bff) {
        this.bff = bff;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Date getCreated_on() {
        return created_on;
    }
    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }
    public Date getModified_on() {
        return modified_on;
    }
    public void setModified_on(Date modified_on) {
        this.modified_on = modified_on;
    }
    public Date getDeleted_on() {
        return deleted_on;
    }
    public void setDeleted_on(Date deleted_on) {
        this.deleted_on = deleted_on;
    }

}
