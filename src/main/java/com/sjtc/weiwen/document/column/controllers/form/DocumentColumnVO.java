package com.sjtc.weiwen.document.column.controllers.form;

import java.io.Serializable;
import java.util.List;

import com.sjtc.weiwen.user.controllers.form.UserVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DocumentColumnVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1358598566666664534L;

	private String oid;

    private String title;

    private String pOid;

    private UserVO user;
    
    private List<DocumentColumnVO> nodes;
    
    private String text;
}
