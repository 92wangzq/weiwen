package com.sjtc.weiwen.document.controllers.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	private String oid;

    private String title;

    private String documentNumber;

    private DocumentColumnVO column;

    private UserVO user;

    private String description;
    
    private FileVO file;

    private Integer downloadNum;

    private String remarks;

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
	
}
