package com.sjtc.weiwen.personnel.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.sjtc.weiwen.personnel.controllers.form.PersonnelVO;
import com.sjtc.weiwen.personnel.services.IPersonnelService;

@RestController
@RequestMapping(value="/personnel", produces="application/json")
public class PersonnelController {

	@Autowired
	private IPersonnelService personnelService;
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResult> save(PersonnelVO personnel, HttpServletRequest req) {
		HttpSession session = req.getSession();
		System.out.println(session.getId());
		return new ResponseEntity<BaseResult>(this.personnelService.save(personnel, req), HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<BaseResult> remove(String oid) {
		return new ResponseEntity<BaseResult>(this.personnelService.delete(oid), HttpStatus.OK);
	}
	
	@RequestMapping(value="/searchPersonnels", method=RequestMethod.GET)
	@RequiresPermissions("personnel:list")
	public @ResponseBody ResponseEntity<PageInfo<PersonnelVO>> searchPersonnels(PersonnelVO personnel, @RequestParam Integer limit, @RequestParam Integer offset, HttpServletRequest req) {
		HttpSession session = req.getSession();
		System.out.println(session.getId());
		return new ResponseEntity<PageInfo<PersonnelVO>>(this.personnelService.getPersonnels(personnel, limit, offset, req), HttpStatus.OK);
	}
	
	@RequestMapping(value="/viewPersonnel", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<PersonnelVO> viewPersonnel(@RequestParam String oid) {
		return new ResponseEntity<PersonnelVO>(this.personnelService.getPersonnel(oid), HttpStatus.OK);
	}
	
	@RequestMapping(value="/exportStuInfoExcel", method=RequestMethod.GET)
	public @ResponseBody void exportStuInfoExcel(PersonnelVO personnel, HttpServletResponse response) {
		this.personnelService.downExcel(personnel, response);
	}
}
