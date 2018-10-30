package com.sjtc.weiwen.document.dao.entity;

public class DocumentAreaEntity {
    private String documentOid;

    private String areaOid;

    public String getDocumentOid() {
        return documentOid;
    }

    public void setDocumentOid(String documentOid) {
        this.documentOid = documentOid == null ? null : documentOid.trim();
    }

    public String getAreaOid() {
        return areaOid;
    }

    public void setAreaOid(String areaOid) {
        this.areaOid = areaOid == null ? null : areaOid.trim();
    }
}