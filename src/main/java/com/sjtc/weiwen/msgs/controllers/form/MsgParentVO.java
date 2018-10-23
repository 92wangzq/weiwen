package com.sjtc.weiwen.msgs.controllers.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class MsgParentVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6879264107672679863L;

	private String oid;
	
	private String title;

    private UserVO sender;

    private String senderOid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;
    
    private List<MsgChildVO> childs;
}
