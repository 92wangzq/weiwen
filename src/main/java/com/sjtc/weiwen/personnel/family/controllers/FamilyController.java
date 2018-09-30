package com.sjtc.weiwen.personnel.family.controllers;

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
import com.sjtc.weiwen.personnel.family.controllers.form.FamilyVO;
import com.sjtc.weiwen.personnel.family.services.IFamilyService;

@RestController
@RequestMapping(value="/family", produces="application/json")
public class FamilyController {

	@Autowired
	private IFamilyService familyService;
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResult> save(FamilyVO family) {
		return new ResponseEntity<BaseResult>(this.familyService.save(family), HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResult> remove(@RequestParam String oid) {
		return new ResponseEntity<BaseResult>(this.familyService.delete(oid), HttpStatus.OK);
	}
	
	@RequestMapping(value="/searchFamilys", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<FamilyVO>> searchFamilys(@RequestParam String personnelOid) {
		return new ResponseEntity<List<FamilyVO>>(this.familyService.getFamilys(personnelOid), HttpStatus.OK);
	}
}
