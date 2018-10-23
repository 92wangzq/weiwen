package com.sjtc.weiwen.system.dao.entity;

import java.io.Serializable;

public class SysDictionaryDataEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1936614137228518321L;

	private String oid;

    private String dictOid;

    private String parentOid;

    private String dictDataName;

    private String dictDataCode;

    private String state;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getDictOid() {
        return dictOid;
    }

    public void setDictOid(String dictOid) {
        this.dictOid = dictOid == null ? null : dictOid.trim();
    }

    public String getParentOid() {
        return parentOid;
    }

    public void setParentOid(String parentOid) {
        this.parentOid = parentOid == null ? null : parentOid.trim();
    }

    public String getDictDataName() {
        return dictDataName;
    }

    public void setDictDataName(String dictDataName) {
        this.dictDataName = dictDataName == null ? null : dictDataName.trim();
    }

    public String getDictDataCode() {
        return dictDataCode;
    }

    public void setDictDataCode(String dictDataCode) {
        this.dictDataCode = dictDataCode == null ? null : dictDataCode.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}