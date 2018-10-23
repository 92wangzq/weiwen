package com.sjtc.weiwen.lawrelated.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LawRelatedEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3776513831940127432L;

	private String oid;

    private String realName;
    
    private String sex;

    private String nation;

    private String identityCard;

    private Date birthday;

    private String politicalStatus;

    private String workUnit;

    private String present;

    private String domicile;

    private String telephone;

    private String appeals;

    private String originalRealName;
    
    private String originalSex;

    private String originalNation;

    private String originalIdentityCard;

    private Date originalBirthday;

    private String originalPoliticalStatus;

    private String originalWorkUnit;

    private String originalPresent;

    private String originalDomicile;

    private String originalTelephone;

    private String originalAppeals;
    
    private String areaOid;

    private String caseType;

    private String letterVisitOrder;

    private String powerAffairsUnit;

    private String attendTo;

    private Date insertTime;

    private Date updateTime;
    
    //查询条件
    private List<String> areaOids;
    
    //返回结果
    private String label;
    
    private Float val;
}