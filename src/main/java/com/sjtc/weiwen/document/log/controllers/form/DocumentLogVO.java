package com.sjtc.weiwen.document.log.controllers.form;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sjtc.weiwen.document.controllers.form.DocumentVO;
import com.sjtc.weiwen.user.controllers.form.UserVO;

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
public class DocumentLogVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5626043797832795797L;
	
	private String oid;

    private UserVO user;

    private DocumentVO document;

    @JsonFormat(timezone = "GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date downloadTime;
}
