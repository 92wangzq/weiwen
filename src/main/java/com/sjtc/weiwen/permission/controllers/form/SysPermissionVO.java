package com.sjtc.weiwen.permission.controllers.form;

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
public class SysPermissionVO {

	private String oid;

    private Boolean available;

    private String name;

    private Long parentId;

    private String parentIds;

    private String permission;

    private String resourceType;

    private String url;
}
