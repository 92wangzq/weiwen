package com.sjtc.weiwen.lawrelated.controllers.form;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class LawRelatedVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3903311143846726868L;

	private String oid;

	@Excel(name="信访人姓名", orderNum = "0")
    private String realName;

	@Excel(name="信访人民族", orderNum="1")
    private String nation;

	@Excel(name="信访人身份证", orderNum="2")
    private String identityCard;

	@Excel(name="信访人出生日期", orderNum="3")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date birthday;

	@Excel(name="信访人政治面貌", orderNum="4")
    private String politicalStatus;

	@Excel(name="信访人工作单位", orderNum="5")
    private String workUnit;

	@Excel(name="信访人现住址", orderNum="6")
    private String present;

	@Excel(name="信访人户籍地", orderNum="7")
    private String domicile;

	@Excel(name="信访人联系方式", orderNum="8")
    private String telephone;

	@Excel(name="信访人信访诉求", orderNum="9")
    private String appeals;

	@Excel(name="原案人姓名", orderNum="10")
    private String originalRealName;

	@Excel(name="原案人民族", orderNum="11")
    private String originalNation;

	@Excel(name="原案人身份证", orderNum="12")
    private String originalIdentityCard;

	@Excel(name="原案人出身日期", orderNum="13")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date originalBirthday;

	@Excel(name="原案人政治面貌", orderNum="14")
    private String originalPoliticalStatus;

	@Excel(name="原案人工作单位", orderNum="15")
    private String originalWorkUnit;

	@Excel(name="原案人现住址", orderNum="16")
    private String originalPresent;

	@Excel(name="原案人户籍地", orderNum="17")
    private String originalDomicile;

	@Excel(name="原案人联系方式", orderNum="18")
    private String originalTelephone;

	@Excel(name="原案人信访诉求", orderNum="19")
    private String originalAppeals;

	@Excel(name="案件类型", replace = {"行政案件_XZ", "刑事案件_XS", "民事案件_MS"}, orderNum="20")
    private String caseType;

	@Excel(name="信访秩序", replace = {"普通访_PT", "重复访_CF", "非访_FF", "越级访_YJ", "打击处理_DJ"}, orderNum="21")
    private String letterVisitOrder;

	@Excel(name="事权单位", orderNum="22")
    private String powerAffairsUnit;

	@Excel(name="信访办理", orderNum="23")
    private String attendTo;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
