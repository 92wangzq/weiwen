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
public class PermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3342719275145431082L;

	private String oid;

    private Boolean available;

    private String name;

    private Long parentId;

    private String parentIds;

    private String permission;

    private String resourceType;

    private String url;
    
    private List<PermissionVO> permissions;
}
