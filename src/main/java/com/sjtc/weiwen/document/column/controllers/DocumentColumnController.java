package com.sjtc.weiwen.document.column.controllers;

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
import com.sjtc.weiwen.document.column.controllers.form.DocumentColumnVO;
import com.sjtc.weiwen.document.column.services.IDocumentColumnService;

@RestController
@RequestMapping(value="/documentColumn", produces="application/json")
public class DocumentColumnController {

	@Autowired
	private IDocumentColumnService documentColumnService;
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResult> save(DocumentColumnVO documentColumn, HttpServletRequest req) {
		return new ResponseEntity<BaseResult>(this.documentColumnService.save(documentColumn, req), HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResult> remove(String oid) {
		return new ResponseEntity<BaseResult>(this.documentColumnService.delete(oid), HttpStatus.OK);
	}
	
	@RequestMapping(value="/searchDocumentColumns", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<PageInfo<DocumentColumnVO>> searchNewsColumns(DocumentColumnVO documentColumn, @RequestParam(name="limit", required=false)Integer limit, @RequestParam(name="offset", required=false)Integer offset) {
		if (limit == null || limit == 0) {
			limit = 1;
		}
		if (offset == null || offset == 0) {
			offset = 10;
		}
		return new ResponseEntity<PageInfo<DocumentColumnVO>>(this.documentColumnService.getDocumentColumns(documentColumn, limit, offset), HttpStatus.OK);
	}
	
	@RequestMapping(value="/children", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<DocumentColumnVO>> children(@RequestParam(name="pOid", required=false)String pOid) {
		return new ResponseEntity<List<DocumentColumnVO>>(this.documentColumnService.getChildrens(pOid), HttpStatus.OK);
	}
}
