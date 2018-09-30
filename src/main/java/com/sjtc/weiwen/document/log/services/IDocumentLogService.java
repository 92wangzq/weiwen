package com.sjtc.weiwen.document.log.services;

import java.util.List;

import com.sjtc.util.BaseResult;
import com.sjtc.weiwen.document.log.controllers.form.DocumentLogVO;

public interface IDocumentLogService {

	BaseResult save(DocumentLogVO logVO);

	List<DocumentLogVO> getDocumentLogs(String documentOid);

}
