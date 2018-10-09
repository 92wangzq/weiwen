package com.sjtc.weiwen.administrative.controllers.form;

import java.io.Serializable;
import java.util.List;

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
public class AdministrativeAreaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1522111055499861498L;

	private String oid;

    private String title;

    private String pOid;
    
    private String nodeId;
    
    private String text;
    
    private List<AdministrativeAreaVO> nodes;
    
    private List<UserVO> users;
}
