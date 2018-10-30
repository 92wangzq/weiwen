package com.sjtc.weiwen.document.controllers.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sjtc.weiwen.administrative.controllers.form.AdministrativeAreaVO;
import com.sjtc.weiwen.document.column.controllers.form.DocumentColumnVO;
import com.sjtc.weiwen.document.log.controllers.form.DocumentLogVO;
import com.sjtc.weiwen.file.controllers.form.FileVO;
import com.sjtc.weiwen.user.controllers.form.UserVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DocumentVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1226450163310915821L;

	/**
	 * 公文状态：审批中
	 */
	public static final String STATE_EXAMINE = "examine";
	
	/**
	 * 公文状态：删除
	 */
	public static final String STATE_DEL = "del";
	
	/**
	 * 公文状态：完成
	 */
	public static final String STATE_COMPLETE = "complete";
	
	/**
	 * 公文状态：未通过
	 */
	public static final String STATE_NOT = "not";

	private String oid;

    private String title;

    private String documentNumber;
    
    private AdministrativeAreaVO area;

    private DocumentColumnVO column;

    private UserVO user;

    private String description;
    
    private FileVO file;

    private Integer downloadNum;

    private String remarks;
    
    private String state;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date insertTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    
    private List<DocumentLogVO> logs;
    
    private String areaOids;
    
    
    /********************* activit **********************/
    //任务办理人
    private String assignee;
    
    //审批结果
    private String status;
    
    //描述
    private String describe;
    
    //任务ID
    private String taskId;
    
    //审批建议
    private String proposal;
	
}
