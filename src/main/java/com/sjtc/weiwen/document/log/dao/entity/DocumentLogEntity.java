package com.sjtc.weiwen.document.log.dao.entity;

import java.util.Date;

public class DocumentLogEntity {
    private String oid;

    private String userOid;

    private String documentOid;

    private Date downloadTime;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getUserOid() {
        return userOid;
    }

    public void setUserOid(String userOid) {
        this.userOid = userOid == null ? null : userOid.trim();
    }

    public String getDocumentOid() {
        return documentOid;
    }

    public void setDocumentOid(String documentOid) {
        this.documentOid = documentOid == null ? null : documentOid.trim();
    }

    public Date getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
    }
}