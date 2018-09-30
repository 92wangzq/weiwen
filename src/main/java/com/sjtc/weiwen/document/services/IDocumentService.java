package com.sjtc.weiwen.document.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.document.controllers.form.DocumentVO;

public interface IDocumentService {

	BaseResult save(DocumentVO document, HttpServletRequest req);

	BaseResult delete(String oid);

	PageInfo<DocumentVO> getDocuments(DocumentVO document, Integer limit, Integer offset);
	
	DocumentVO getDocument(String oid);

	void download(String oid, HttpServletRequest req, HttpServletResponse res);

}
