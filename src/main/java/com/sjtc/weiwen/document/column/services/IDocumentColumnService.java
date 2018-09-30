package com.sjtc.weiwen.document.column.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.document.column.controllers.form.DocumentColumnVO;

public interface IDocumentColumnService {

	BaseResult save(DocumentColumnVO documentColumn, HttpServletRequest req);

	BaseResult delete(String oid);

	PageInfo<DocumentColumnVO> getDocumentColumns(DocumentColumnVO documentColumn, Integer limit, Integer offset);

	List<DocumentColumnVO> getChildrens(String pOid);

	DocumentColumnVO getDocumentColumn(String oid);

}
