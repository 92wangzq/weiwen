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
public class SysRoleEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3637875754057077785L;

	private String oid;

    private Boolean available;

    private String description;

    private String role;
    
    private String name;

}