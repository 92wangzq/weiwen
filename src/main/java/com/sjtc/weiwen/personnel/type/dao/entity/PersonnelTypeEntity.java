package com.sjtc.weiwen.personnel.type.dao.entity;

public class PersonnelTypeEntity {
    private String oid;

    private String title;

    private String pOid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getpOid() {
        return pOid;
    }

    public void setpOid(String pOid) {
        this.pOid = pOid == null ? null : pOid.trim();
    }
}