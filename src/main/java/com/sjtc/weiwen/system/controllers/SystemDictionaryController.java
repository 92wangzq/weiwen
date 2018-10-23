package com.sjtc.weiwen.system.controllers;

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
import com.sjtc.weiwen.system.controllers.form.DictionaryDataVO;
import com.sjtc.weiwen.system.controllers.form.DictionaryVO;
import com.sjtc.weiwen.system.services.ISystemDictionaryService;

@RestController
@RequestMapping(value = "/dictionary", produces = "application/json")
public class SystemDictionaryController {

	@Autowired
	private ISystemDictionaryService systemDictionaryService;

	@RequestMapping(name = "新增或修改字典类型", value = "/saveDictionary", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<BaseResult> saveDictionary(DictionaryVO vo) {
		return new ResponseEntity<BaseResult>(this.systemDictionaryService.saveDictionary(vo), HttpStatus.OK);
	}

	@RequestMapping(name = "删除字典类型", value = "/removeDictionary", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<BaseResult> removeDictionary(@RequestParam("oid") String oid) {
		return new ResponseEntity<BaseResult>(this.systemDictionaryService.deleteDictionary(oid), HttpStatus.OK);
	}

	@RequestMapping(name = "字典类型列表", value = "/searchDictionarys", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<PageInfo<DictionaryVO>> searchDictionarys(DictionaryVO vo,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset) {

		return new ResponseEntity<PageInfo<DictionaryVO>>(
				this.systemDictionaryService.getDictionarys(vo, limit, offset), HttpStatus.OK);
	}

	@RequestMapping(name = "新增或修改字典数据", value = "/saveDictionaryData", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<BaseResult> saveDictionaryData(DictionaryDataVO vo) {
		return new ResponseEntity<BaseResult>(this.systemDictionaryService.saveDictionaryData(vo), HttpStatus.OK);
	}

	@RequestMapping(name = "删除字典数据", value = "/removeDictionaryData", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<BaseResult> removeDictionaryData(@RequestParam(value = "oid") String oid) {
		return new ResponseEntity<BaseResult>(this.systemDictionaryService.deleteDictionaryData(oid), HttpStatus.OK);
	}

	@RequestMapping(name = "获取父级字典数据", value = "/searchParentDictionaryDatas", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<List<DictionaryDataVO>> searchParentDictionaryDatas(
			@RequestParam(value = "dictionaryValue") String dictionaryValue) {
		return new ResponseEntity<List<DictionaryDataVO>>(
				this.systemDictionaryService.getParentDictionaryDatas(dictionaryValue), HttpStatus.OK);
	}

	@RequestMapping(name = "获取子级字典数据", value = "/searchChildDictionaryDatas", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<List<DictionaryDataVO>> searchChildDictionaryDatas(
			@RequestParam(value = "parentOid") String parentOid) {
		return new ResponseEntity<List<DictionaryDataVO>>(
				this.systemDictionaryService.getChildDictionaryDatas(parentOid), HttpStatus.OK);
	}
}
