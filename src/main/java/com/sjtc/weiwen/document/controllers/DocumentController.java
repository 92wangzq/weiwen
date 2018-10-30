package com.sjtc.weiwen.document.controllers;

import javax.servlet.http.HttpServletRequest;
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
import com.sjtc.weiwen.document.controllers.form.DocumentVO;
import com.sjtc.weiwen.document.services.IDocumentService;

@RestController
@RequestMapping(value="/document", produces="application/json")
public class DocumentController {

	@Autowired
	private IDocumentService documentService;
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResult> save(DocumentVO document, HttpServletRequest req) {
		return new ResponseEntity<BaseResult>(this.documentService.save(document, req), HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResult> remove(@RequestParam(name="oid")String oid) {
		return new ResponseEntity<BaseResult>(this.documentService.delete(oid), HttpStatus.OK);
	}
	
	@RequestMapping(value="/searchDocuments", method= {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResponseEntity<PageInfo<DocumentVO>> searchDocuments(DocumentVO document, @RequestParam(name="limit", required=false)Integer limit, @RequestParam(name="offset", required=false)Integer offset) {
		if (limit == null || limit == 0) {
			limit = 1;
		}
		if (offset == null || offset == 0) {
			offset = 10;
		}
		return new ResponseEntity<PageInfo<DocumentVO>>(this.documentService.getDocuments(document, limit, offset), HttpStatus.OK);
	}
	
	@RequestMapping(name = "获取待办", value = "/tasks", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<PageInfo<DocumentVO>> tasks(DocumentVO document, @RequestParam(name="limit", required=false)Integer limit, @RequestParam(name="offset", required=false)Integer offset) {
		if (limit == null || limit == 0) {
			limit = 1;
		}
		if (offset == null || offset == 0) {
			offset = 10;
		}
		return new ResponseEntity<PageInfo<DocumentVO>>(this.documentService.getTaskDocuments(document, limit, offset), HttpStatus.OK);
	}
	
	@RequestMapping(value="/viewDocument", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<DocumentVO> viewDocument(String oid) {
		return new ResponseEntity<DocumentVO>(this.documentService.getDocument(oid), HttpStatus.OK);
	}
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void download(@RequestParam String oid, HttpServletRequest req, HttpServletResponse res) {
		this.documentService.download(oid, req, res);
	}
	
	@RequestMapping(name = "审批公文", value = "/approvalDocument", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<BaseResult> approvalDocument(DocumentVO document) {
		return new ResponseEntity<BaseResult>(this.documentService.approval(document), HttpStatus.OK);
	}
}
