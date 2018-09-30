package com.sjtc.weiwen.document.column.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.document.column.controllers.form.DocumentColumnVO;
import com.sjtc.weiwen.document.column.dao.DocumentColumnEntityMapper;
import com.sjtc.weiwen.document.column.dao.entity.DocumentColumnEntity;
import com.sjtc.weiwen.document.column.services.IDocumentColumnService;
import com.sjtc.weiwen.user.services.IUserService;

@Service
public class DocumentColumnServiceImpl implements IDocumentColumnService {

	@Autowired
	private DocumentColumnEntityMapper documentColumnMapper;
	@Autowired
	private IUserService userService;
	
	@Transactional
	@Override
	public BaseResult save(DocumentColumnVO documentColumn, HttpServletRequest req) {
		DocumentColumnEntity entity = new DocumentColumnEntity();
		if (StringUtil.isEmpty(documentColumn.getOid())) {
			entity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
			entity.setTitle(documentColumn.getTitle());
			entity.setpOid(documentColumn.getPOid());
			entity.setUserOid(req.getSession().getAttribute("user").toString());
			this.documentColumnMapper.insert(entity);
		} else {
			entity.setOid(documentColumn.getOid());
			entity.setTitle(documentColumn.getTitle());
			entity.setpOid(documentColumn.getPOid());
			this.documentColumnMapper.updateByPrimaryKeySelective(entity);
		}
		return new BaseResult();
	}
	
	@Transactional
	@Override
	public BaseResult delete(String oid) {
		this.documentColumnMapper.deleteByPrimaryKey(oid);
		return new BaseResult();
	}
	
	@Override
	public PageInfo<DocumentColumnVO> getDocumentColumns(DocumentColumnVO documentColumn, Integer limit,
			Integer offset) {
		DocumentColumnEntity params = new DocumentColumnEntity();
		params.setOid(documentColumn.getOid());
		params.setTitle(documentColumn.getTitle());
		params.setpOid(documentColumn.getPOid());
		Page<DocumentColumnEntity> page = PageHelper.startPage(offset, limit, true);
		List<DocumentColumnEntity> list = this.documentColumnMapper.selectDocumentColumnsByFuzz(params);
		if (list != null && list.size() > 0) {
			List<DocumentColumnVO> vos = new ArrayList<>();
			for (DocumentColumnEntity entity : list) {
				DocumentColumnVO vo = new DocumentColumnVO();
				vo.setOid(entity.getOid());
				vo.setPOid(entity.getpOid());
				vo.setTitle(entity.getTitle());
				vo.setUser(this.userService.getUser(entity.getUserOid()));
				vos.add(vo);
			}
			PageInfo<DocumentColumnVO> pageInfo = new PageInfo<>();
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
	public List<DocumentColumnVO> getChildrens(String pOid) {
		List<DocumentColumnEntity> list = this.documentColumnMapper.selectChildrensByPOid(pOid);
		if (list != null && list.size() > 0) {
			List<DocumentColumnVO> vos = new ArrayList<>();
			for (DocumentColumnEntity entity : list) {
				DocumentColumnVO vo = new DocumentColumnVO();
				vo.setOid(entity.getOid());
				vo.setTitle(entity.getTitle());
				vo.setPOid(entity.getpOid());
				vo.setUser(this.userService.getUser(entity.getUserOid()));
				vo.setText(entity.getTitle());
				vo.setNodes(this.getChildrens(entity.getOid()));
				vos.add(vo);
			}
			return vos;
		}
		return null;
	}

	@Override
	public DocumentColumnVO getDocumentColumn(String oid) {
		DocumentColumnEntity entity = this.documentColumnMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			DocumentColumnVO vo = new DocumentColumnVO();
			vo.setOid(entity.getOid());
			vo.setTitle(entity.getTitle());
			vo.setPOid(entity.getpOid());
			vo.setUser(this.userService.getUser(entity.getUserOid()));
			return vo;
		}
		return null;
	}
}
