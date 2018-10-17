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
public class SysPermissionEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -857217617045242203L;

	private String oid;

    private Boolean available;

    private String name;

    private String parentId;

    private String parentIds;

    private String permission;

    private String resourceType;

    private String url;
}