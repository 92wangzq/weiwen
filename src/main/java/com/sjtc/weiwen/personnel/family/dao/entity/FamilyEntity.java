package com.sjtc.weiwen.personnel.family.dao.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode
public class FamilyEntity implements Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6779519901837977981L;
	
	private String oid;

    private String personnelOid;

    private String relationship;

    private String realName;

    private String nation;

    private String identityCard;

    private String workUnit;

    private String domicile;

    private String present;

    private String telephone;

    private Date insertTime;

    private Date editTime;
}