package com.sjtc.weiwen.administrative.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.administrative.controllers.form.AdministrativeAreaVO;
import com.sjtc.weiwen.administrative.services.IAdministrativeAreaService;
import com.sjtc.weiwen.user.controllers.form.UserVO;
import com.sjtc.weiwen.user.services.IUserService;

@RestController
@RequestMapping(value="/area", produces="application/json")
public class AdministrativeAreaController {

	@Autowired
	private IAdministrativeAreaService administrativeAreaService;
	@Autowired
	private IUserService userService;
	/**
	 * 新增/修改行政区域
	 * @param form
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResult> save(AdministrativeAreaVO vo) {
		return new ResponseEntity<BaseResult>(this.administrativeAreaService.save(vo), HttpStatus.OK);
	}
	
	/**
	 * 删除行政区域
	 * @param oid
	 * @return
	 */
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<BaseResult> remove(@RequestParam String oid) {
		return new ResponseEntity<BaseResult>(this.administrativeAreaService.delete(oid), HttpStatus.OK);
	}
	
	/**
	 * 行政区域列表
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/searchAreas", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<PageInfo<AdministrativeAreaVO>> searchAreas(AdministrativeAreaVO vo, @RequestParam Integer limit, @RequestParam Integer offset) {
		return new ResponseEntity<PageInfo<AdministrativeAreaVO>>(this.administrativeAreaService.getAreas(vo, limit, offset), HttpStatus.OK);
	}
	
	/**
	 * 获取子级行政区域
	 * @param oid
	 * @return
	 */
	@RequestMapping(value="/children", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<AdministrativeAreaVO>> children(@RequestParam(name="pOid", required=false)String pOid, HttpServletRequest req) {
		if (pOid == null) {
			UserVO userVO = this.userService.getUser(req.getSession().getAttribute("user").toString());
//			UserVO userVO = (UserVO)SecurityUtils.getSubject().getPrincipal();
			pOid = userVO.getArea().getOid();
		}
		return new ResponseEntity<List<AdministrativeAreaVO>>(this.administrativeAreaService.getAreaParent(pOid), HttpStatus.OK);
	}
}
