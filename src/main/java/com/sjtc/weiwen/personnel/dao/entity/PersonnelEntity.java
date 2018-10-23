package com.sjtc.weiwen.personnel.dao.entity;

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
public class PersonnelEntity {
    private String oid;

    private String userOid;

    private String areaOid;

    private String personnelTypeOid;

    private String realName;

    private String sex;

    private String nation;

    private String identityCard;

    private Date birthday;

    private String workUnit;

    private String politicalStatus;

    private String domicile;

    private String present;

    private String telephone;

    private String realState;

    private Integer more;

    private String riskRating;

    private String eventAppeals;

    private Date insertTime;

    private Date editTime;

    private String experienceResults;
    
    //查询条件
    private List<String> areaOids;
    
    private String familyRealName;
}