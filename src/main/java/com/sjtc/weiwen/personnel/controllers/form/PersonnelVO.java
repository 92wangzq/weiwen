package com.sjtc.weiwen.personnel.controllers.form;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sjtc.weiwen.administrative.controllers.form.AdministrativeAreaVO;
import com.sjtc.weiwen.personnel.type.controllers.form.PersonnelTypeVO;
import com.sjtc.weiwen.user.controllers.form.UserVO;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
public class PersonnelVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4269246310354249599L;

	private String oid;

    private UserVO user;

    private AdministrativeAreaVO area;

    private PersonnelTypeVO type;

    @Excel(name = "姓名", orderNum = "0")
    private String realName;

    @Excel(name = "性别", replace = {"男_1", "女_0"}, orderNum = "1")
    private String sex;

    @Excel(name = "民族", orderNum = "2")
    private String nation;

    private String identityCard;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
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
    
    private String familyJson;
    
    private String familyRealName;
}
