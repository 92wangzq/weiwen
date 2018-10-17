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
public class SysUserRoleEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 8248229212090109498L;

	private String userOid;

    private String roleOid;
}