package com.sjtc.weiwen.news.controllers.form;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sjtc.weiwen.news.column.controllers.form.NewsColumnVO;
import com.sjtc.weiwen.user.controllers.form.UserVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class NewsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4900525660018591100L;

	private String oid;

    private UserVO user;

    private NewsColumnVO column;

    private String title;

    private String fileOid;

    @JsonFormat(timezone = "GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date insertTime;

    private String content;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}
