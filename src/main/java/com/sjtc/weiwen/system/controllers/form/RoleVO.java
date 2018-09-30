package com.sjtc.weiwen.system.controllers.form;

import java.io.Serializable;
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
public class RoleVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4725557284919534398L;

	private String oid;

    private Boolean available;

    private String description;

    private String role;
    
    private List<PermissionVO> permissions;
}
