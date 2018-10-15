package com.sjtc.weiwen.lawrelated.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.sjtc.weiwen.lawrelated.controllers.form.CaseTypeStatisticalVO;
import com.sjtc.weiwen.lawrelated.controllers.form.DateStatisticalVO;
import com.sjtc.weiwen.lawrelated.controllers.form.LawRelatedVO;
import com.sjtc.weiwen.lawrelated.controllers.form.SexStatisticalVO;
import com.sjtc.weiwen.lawrelated.services.ILawRelatedService;

@RestController
@RequestMapping(value = "/lawRelated", produces = "application/json")
public class LawRelatedController {

	@Autowired
	private ILawRelatedService lawRelatedService;

	@RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<BaseResult> save(LawRelatedVO vo) {
		return new ResponseEntity<BaseResult>(this.lawRelatedService.save(vo), HttpStatus.OK);
	}

	@RequestMapping(value = "/remove", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<BaseResult> remove(@RequestParam(value = "oid", required = true) String oid) {
		return new ResponseEntity<BaseResult>(this.lawRelatedService.delete(oid), HttpStatus.OK);
	}

	@RequestMapping(value = "/lawRelatedList", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<PageInfo<LawRelatedVO>> lawRelatedList(LawRelatedVO vo,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset) {
		return new ResponseEntity<PageInfo<LawRelatedVO>>(this.lawRelatedService.getlawRelateds(vo, limit, offset),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/viewLawRelated", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<LawRelatedVO> viewLawRelated(
			@RequestParam(value = "oid", required = true) String oid) {
		return new ResponseEntity<LawRelatedVO>(this.lawRelatedService.getLawRelated(oid), HttpStatus.OK);
	}

	/**
	 * 导出报表
	 * 
	 * @param vo
	 * @param response
	 */
	@RequestMapping(value = "/exportStuInfoExcel", method = RequestMethod.GET)
	public @ResponseBody void exportStuInfoExcel(LawRelatedVO vo, HttpServletResponse response) {
		this.lawRelatedService.downExcel(vo, response);
	}

	/**
	 * 根据案件类型统计
	 * 
	 * @return
	 */
	@RequestMapping(value = "/caseTypeStatistical", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<List<CaseTypeStatisticalVO>> caseTypeStatistical() {
		return new ResponseEntity<List<CaseTypeStatisticalVO>>(this.lawRelatedService.caseTypeStatistical(), HttpStatus.OK);
	}
	
	/**
	 * 案件类型统计饼状图
	 * @return
	 */
	@RequestMapping(value = "/caseTypeStatisticalDonut", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<List<CaseTypeStatisticalVO>> caseTypeStatisticalDonut() {
		return new ResponseEntity<List<CaseTypeStatisticalVO>>(this.lawRelatedService.getCaseTypeStatisticalDonut(), HttpStatus.OK);
	}

	/**
	 * 根据日期统计
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dateStatistical", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<DateStatisticalVO> dateStatistical() {
		return null;
	}

	/**
	 * 根据性别统计
	 * 
	 * @return
	 */
	@RequestMapping(value = "sexStatistical", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<SexStatisticalVO> sexStatistical() {
		return null;
	}
}
