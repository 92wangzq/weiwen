package com.sjtc.weiwen.system.controllers.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * 用于角色管理--编辑--回显(默认选中)
	 */
	private static Map<String, Object> stateMap = new HashMap<String, Object>();

	static {
		stateMap.put("checked", true);
	}

	private String oid;

	private Boolean available;

	private String name;

	private PermissionVO parent;

	private String parentIds;

	private String permission;

	private String resourceType;

	private String url;

	private List<PermissionVO> permissions;

	private String nodeId;

	private String text;

	private List<PermissionVO> nodes;

	private Map<String, Object> state;

	/**
	 * 用于角色管理--编辑--回显（根据逻辑判定是否需要默认选中，如需要则调用此方法赋值）
	 */
	public void setState() {
		this.state = stateMap;
	}
}
