package com.sjtc.weiwen.system.dao.entity;

import java.io.Serializable;

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
public class SysRolePermissionEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5598273519427427435L;

	private String permissionOid;

    private String roleOid;
}