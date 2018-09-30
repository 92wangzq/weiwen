package com.sjtc.weiwen.document.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.document.column.services.IDocumentColumnService;
import com.sjtc.weiwen.document.controllers.form.DocumentVO;
import com.sjtc.weiwen.document.dao.DocumentEntityMapper;
import com.sjtc.weiwen.document.dao.entity.DocumentEntity;
import com.sjtc.weiwen.document.log.controllers.form.DocumentLogVO;
import com.sjtc.weiwen.document.log.services.IDocumentLogService;
import com.sjtc.weiwen.document.services.IDocumentService;
import com.sjtc.weiwen.file.services.IFileService;
import com.sjtc.weiwen.user.services.IUserService;

@Service
public class DocumentServiceImpl implements IDocumentService {

	@Autowired
	private DocumentEntityMapper documentMapper;
	@Autowired
	private IDocumentColumnService documentColumnService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IFileService fileService;
	@Autowired
	private IDocumentLogService documentLogService;

	@Transactional
	@Override
	public BaseResult save(DocumentVO document, HttpServletRequest req) {
		if (StringUtil.isEmpty(document.getOid())) {
			DocumentEntity entity = new DocumentEntity();
			entity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
			entity.setDocumentNumber(date.format(new Date()));
			entity.setTitle(document.getTitle());
			entity.setFileOid(document.getFile().getOid());
			entity.setColumnOid(document.getColumn().getOid());
			entity.setUserOid(req.getSession().getAttribute("user").toString());
			entity.setDescription(document.getDescription());
			entity.setDownloadNum(0);
			entity.setRemarks(document.getRemarks());
			entity.setInsertTime(document.getInsertTime());
			entity.setUpdateTime(new Date());
			this.documentMapper.insert(entity);
		} else {
			DocumentEntity entity = this.documentMapper.selectByPrimaryKey(document.getOid());
			if (entity == null) {
				return new BaseResult("-1", "document oid not find", null);
			}
			entity.setTitle(document.getTitle());
			entity.setFileOid(document.getFile().getOid());
			entity.setColumnOid(document.getColumn().getOid());
			entity.setDescription(document.getDescription());
			entity.setRemarks(document.getRemarks());
			entity.setUpdateTime(new Date());
			this.documentMapper.updateByPrimaryKeySelective(entity);
		}
		return new BaseResult();
	}

	@Transactional
	@Override
	public BaseResult delete(String oid) {
		this.documentMapper.deleteByPrimaryKey(oid);
		return new BaseResult();
	}

	@Override
	public PageInfo<DocumentVO> getDocuments(DocumentVO document, Integer limit, Integer offset) {
		DocumentEntity params = new DocumentEntity();
		params.setColumnOid(document.getColumn() != null ? document.getColumn().getOid() : null);
		params.setTitle(document.getTitle());
		params.setStartTime(document.getStartTime());
		params.setEndTime(document.getEndTime());
		Page<DocumentEntity> page = PageHelper.startPage(offset, limit, true);
		List<DocumentEntity> list = this.documentMapper.selectDocumentsByFuzz(params);
		if (list != null && list.size() > 0) {
			List<DocumentVO> vos = new ArrayList<>();
			for (DocumentEntity entity : list) {
				DocumentVO vo = new DocumentVO();
				vo.setOid(entity.getOid());
				vo.setDocumentNumber(entity.getDocumentNumber());
				vo.setTitle(entity.getTitle());
				vo.setDescription(entity.getDescription());
				vo.setDownloadNum(entity.getDownloadNum());
				vo.setRemarks(entity.getRemarks());
				vo.setInsertTime(entity.getInsertTime());
				vo.setColumn(this.documentColumnService.getDocumentColumn(entity.getColumnOid()));
				vo.setUser(this.userService.getUser(entity.getUserOid()));
				vo.setFile(this.fileService.getFile(entity.getFileOid()));
				vos.add(vo);
			}
			PageInfo<DocumentVO> pageInfo = new PageInfo<>();
			pageInfo.setPageNum(page.getPageNum());
			pageInfo.setPageSize(page.getPageSize());
			pageInfo.setPages(page.getPages());
			pageInfo.setTotal(page.getTotal());
			pageInfo.setRows(vos);
			return pageInfo;
		}
		return null;
	}
	
	@Override
	public DocumentVO getDocument(String oid) {
		DocumentEntity entity = this.documentMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			DocumentVO vo = new DocumentVO();
			vo.setOid(entity.getOid());
			vo.setDocumentNumber(entity.getDocumentNumber());
			vo.setTitle(entity.getTitle());
			vo.setDescription(entity.getDescription());
			vo.setDownloadNum(entity.getDownloadNum());
			vo.setRemarks(entity.getRemarks());
			vo.setInsertTime(entity.getInsertTime());
			vo.setColumn(this.documentColumnService.getDocumentColumn(entity.getColumnOid()));
			vo.setUser(this.userService.getUser(entity.getUserOid()));
			vo.setFile(this.fileService.getFile(entity.getFileOid()));
			vo.setLogs(this.documentLogService.getDocumentLogs(entity.getOid()));
			return vo;
		}
		return null;
	}

	@Override
	public void download(String oid, HttpServletRequest req, HttpServletResponse res) {
		DocumentVO vo = this.getDocument(oid);
		if (vo == null) {
			throw new IllegalArgumentException();
		}
		fileService.download(vo.getFile().getOid(), req, res);
		DocumentLogVO logVO = new DocumentLogVO();
		logVO.setDocument(vo);
		logVO.setUser(this.userService.getUser(req.getSession().getAttribute("user").toString()));
		this.documentLogService.save(logVO);
		
		this.updateDocumentDownloadNum(oid);
	}

	@Transactional
	private void updateDocumentDownloadNum(String oid) {
		DocumentEntity entity = this.documentMapper.selectByPrimaryKey(oid);
		entity.setDownloadNum(entity.getDownloadNum()+1);
		this.documentMapper.updateByPrimaryKeySelective(entity);
	}
}
