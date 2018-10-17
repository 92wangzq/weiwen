package com.sjtc.weiwen.system.controllers;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.system.controllers.form.PermissionVO;
import com.sjtc.weiwen.system.controllers.form.RoleVO;
import com.sjtc.weiwen.system.services.ISystemService;
import com.sjtc.weiwen.user.controllers.form.UserVO;

@RestController
@RequestMapping(value = "/system", produces = "application/json")
public class SystemController {

	@Autowired
	private ISystemService systemService;

	@RequestMapping(name = "获取所有一级资源列表", value = "/searchParentPermissions", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<PageInfo<PermissionVO>> searchParentPermissions(PermissionVO vo, @RequestParam Integer limit,
			@RequestParam Integer offset) {
		return new ResponseEntity<PageInfo<PermissionVO>>(this.systemService.getParentPermissions(vo, limit, offset),
				HttpStatus.OK);
	}

	@RequestMapping(name = "根据父级ID获取子级列表", value = "/searchChildPermissions", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<List<PermissionVO>> searchChildPermissions(
			@RequestParam(value = "parentId") String parentId) {
		return new ResponseEntity<List<PermissionVO>>(this.systemService.getChildPermissions(parentId), HttpStatus.OK);
	}

	@RequestMapping(name = "获取资源treeview列表", value = "/permissionTreeview", method = { RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<List<PermissionVO>> permissionTreeview(
			@RequestParam(value = "roleOid", required = false) String roleOid) {
		return new ResponseEntity<List<PermissionVO>>(this.systemService.getParentPermissionTreeview(roleOid),
				HttpStatus.OK);
	}

	@RequestMapping(name = "新增或修改角色", value = "/saveRole", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<BaseResult> saveRole(RoleVO vo) {
		return new ResponseEntity<BaseResult>(this.systemService.saveRole(vo), HttpStatus.OK);
	}

	@RequestMapping(name = "删除角色", value = "/removeRole", method = { RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<BaseResult> removeRole(@RequestParam(value = "oid") String oid) {
		return new ResponseEntity<BaseResult>(this.systemService.deleteRole(oid), HttpStatus.OK);
	}

	@RequestMapping(value = "/roleList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<PageInfo<RoleVO>> roleList(RoleVO vo,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset) {
		return new ResponseEntity<PageInfo<RoleVO>>(this.systemService.getRoles(vo, limit, offset), HttpStatus.OK);
	}

	@RequestMapping(value = "/menus", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<List<PermissionVO>> menus() {
		Subject currentUser = SecurityUtils.getSubject();
		String userJson = JSON.toJSON(currentUser.getPrincipal()).toString();
		UserVO user = JSON.parseObject(userJson, UserVO.class);

		return new ResponseEntity<List<PermissionVO>>(this.systemService.getMenus(user.getOid(), null), HttpStatus.OK);
	}

}
