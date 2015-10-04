package com.m2p.web.model;

import java.sql.Timestamp;

/**
 * Created by sriramk on 10/14/2014.
 */
public class AbstractEntity {

    private static final long serialVersionUID = 7665388940974264209L;

    private Boolean deleted;
    private String creator;
    private Timestamp created;
    private String changer;
    private Timestamp changed;
    private Long version;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getChanger() {
        return changer;
    }

    public void setChanger(String changer) {
        this.changer = changer;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public void setChanged(Timestamp changed) {
        this.changed = changed;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
