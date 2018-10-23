package com.sjtc.weiwen.system.dao.entity;

import java.io.Serializable;

public class SysDictionaryEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8885172006434052604L;

	private String oid;

    private String dictName;

    private String dictValue;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }
}