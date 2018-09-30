package com.sjtc.weiwen.personnel.family.controllers.form;

import java.util.Date;

import com.sjtc.weiwen.personnel.controllers.form.PersonnelVO;

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
public class FamilyVO {

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
    
    private PersonnelVO Personnel;
}
