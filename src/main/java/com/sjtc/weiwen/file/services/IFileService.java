package com.sjtc.weiwen.file.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sjtc.weiwen.file.controllers.form.FileVO;

public interface IFileService {

	void save(FileVO vo);

	FileVO getFile(String oid);

	void download(String oid, HttpServletRequest req, HttpServletResponse res);

}
