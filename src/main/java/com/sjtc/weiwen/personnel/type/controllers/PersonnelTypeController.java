package com.sjtc.weiwen.personnel.type.controllers;

import java.util.List;

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
import com.sjtc.weiwen.personnel.type.controllers.form.PersonnelTypeVO;
import com.sjtc.weiwen.personnel.type.services.IPersonnelTypeService;

@RestController
@RequestMapping(value="/personnelType", produces="application/json")
public class PersonnelTypeController {

	@Autowired
	private IPersonnelTypeService personnelTypeService;
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResult> save(PersonnelTypeVO type) {
		return new ResponseEntity<BaseResult>(this.personnelTypeService.save(type), HttpStatus.OK);
	}
	
	@RequestMapping(value="/searchTypes", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<PageInfo<PersonnelTypeVO>> searchTypes(@RequestParam Integer limit, @RequestParam Integer offset) {
		return new ResponseEntity<PageInfo<PersonnelTypeVO>>(this.personnelTypeService.getTypes(limit, offset), HttpStatus.OK);
	}
	
	@RequestMapping(value="/children", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<PersonnelTypeVO>> children(@RequestParam String pOid) {
		return new ResponseEntity<List<PersonnelTypeVO>>(this.personnelTypeService.getTypeChildren(pOid), HttpStatus.OK);
	}
}
