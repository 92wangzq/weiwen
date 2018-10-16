package com.sjtc.weiwen.news.column.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.administrative.services.IAdministrativeAreaService;
import com.sjtc.weiwen.news.column.controllers.form.NewsColumnVO;
import com.sjtc.weiwen.news.column.dao.NewsColumnEntityMapper;
import com.sjtc.weiwen.news.column.dao.entity.NewsColumnEntity;
import com.sjtc.weiwen.news.column.services.INewsColumnService;
import com.sjtc.weiwen.user.controllers.form.UserVO;
import com.sjtc.weiwen.user.services.IUserService;

@Service
public class NewsColumnServiceImpl implements INewsColumnService {

	@Autowired
	private NewsColumnEntityMapper newsColumnMapper;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAdministrativeAreaService administrativeAreaService;

	@Transactional
	@Override
	public BaseResult save(NewsColumnVO newsColumn, HttpServletRequest req) {
		NewsColumnEntity entity = new NewsColumnEntity();
		if (StringUtil.isEmpty(newsColumn.getOid())) {
			entity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
			entity.setTitle(newsColumn.getTitle());
			entity.setPOid(!StringUtils.isEmpty(newsColumn.getParent().getOid()) ? newsColumn.getParent().getOid() : null);
			entity.setUserOid(req.getSession().getAttribute("user").toString());
			entity.setAreaOid(newsColumn.getArea().getOid());
			this.newsColumnMapper.insertSelective(entity);
		} else {
			entity.setOid(newsColumn.getOid());
			entity.setTitle(newsColumn.getTitle());
			entity.setPOid(newsColumn.getParent() != null ? newsColumn.getParent().getOid() : null);
			entity.setAreaOid(newsColumn.getArea().getOid());
			this.newsColumnMapper.updateByPrimaryKeySelective(entity);
		}
		return new BaseResult();
	}

	@Transactional
	@Override
	public BaseResult delete(String oid) {
		this.newsColumnMapper.deleteByPrimaryKey(oid);
		return new BaseResult();
	}

	@Override
	public PageInfo<NewsColumnVO> getNewsColumns(NewsColumnVO newsColumn, Integer limit, Integer offset, HttpServletRequest request) {
		UserVO user = this.userService.getUser(request.getSession().getAttribute("user").toString());
		NewsColumnEntity params = new NewsColumnEntity();
		List<String> areaOids = new ArrayList<>();
		if (newsColumn.getArea() != null && !StringUtils.isEmpty(newsColumn.getArea().getOid())) {
			areaOids = this.administrativeAreaService.getChildOids(newsColumn.getArea().getOid());
			if (!CollectionUtils.isEmpty(areaOids)) {
				params.setAreaOids(areaOids);
			}
		} else {
			areaOids = this.administrativeAreaService.getChildOids(user.getArea().getOid());
			if (!CollectionUtils.isEmpty(areaOids)) {
				params.setAreaOids(areaOids);
			}
		}
		params.setTitle(newsColumn.getTitle());
		params.setPOid(newsColumn.getParent() != null ? newsColumn.getParent().getOid() : null);
		Page<NewsColumnEntity> page = PageHelper.startPage(offset, limit, true);
		List<NewsColumnEntity> list = this.newsColumnMapper.selectNewsColumnsByFuzz(params);
		if (list != null && list.size() > 0) {
			List<NewsColumnVO> vos = new ArrayList<>();
			for (NewsColumnEntity entity : list) {
				NewsColumnVO vo = new NewsColumnVO();
				vo.setOid(entity.getOid());
				vo.setParent(this.getNewsColumn(entity.getPOid()));
				vo.setTitle(entity.getTitle());
				vo.setUser(this.userService.getUser(entity.getUserOid()));
				vos.add(vo);
			}
			PageInfo<NewsColumnVO> pageInfo = new PageInfo<>();
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
	public List<NewsColumnVO> getChildrens(String pOid) {
		List<NewsColumnEntity> list = this.newsColumnMapper.selectChildrensByPOid(pOid);
		if (list != null && list.size() > 0) {
			List<NewsColumnVO> vos = new ArrayList<>();
			for (NewsColumnEntity entity : list) {
				NewsColumnVO vo = new NewsColumnVO();
				vo.setOid(entity.getOid());
				vo.setTitle(entity.getTitle());
				vo.setParent(this.getNewsColumn(entity.getPOid()));
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
	public NewsColumnVO getNewsColumn(String oid) {
		NewsColumnEntity entity = this.newsColumnMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			NewsColumnVO vo = new NewsColumnVO();
			vo.setOid(entity.getOid());
			vo.setTitle(entity.getTitle());
			vo.setUser(this.userService.getUser(entity.getUserOid()));
			return vo;
		}
		return null;
	}

	@Override
	public PageInfo<NewsColumnVO> getParentNewsColumns(NewsColumnVO newsColumn, Integer limit, Integer offset, HttpServletRequest request) {
		UserVO user = this.userService.getUser(request.getSession().getAttribute("user").toString());
		NewsColumnEntity params = new NewsColumnEntity();
		List<String> areaOids = new ArrayList<>();
		if (newsColumn.getArea() != null && !StringUtils.isEmpty(newsColumn.getArea().getOid())) {
			areaOids = this.administrativeAreaService.getChildOids(newsColumn.getArea().getOid());
			if (!CollectionUtils.isEmpty(areaOids)) {
				params.setAreaOids(areaOids);
			}
		} else {
			areaOids = this.administrativeAreaService.getChildOids(user.getArea().getOid());
			if (!CollectionUtils.isEmpty(areaOids)) {
				params.setAreaOids(areaOids);
			}
		}
		params.setTitle(newsColumn.getTitle());
		Page<NewsColumnEntity> page = PageHelper.startPage(offset, limit, true);
		List<NewsColumnEntity> list = this.newsColumnMapper.selectParentNewsColumns(params);
		if (list != null && list.size() > 0) {
			List<NewsColumnVO> vos = new ArrayList<>();
			for (NewsColumnEntity entity : list) {
				NewsColumnVO vo = new NewsColumnVO();
				vo.setOid(entity.getOid());
				vo.setParent(this.getNewsColumn(entity.getPOid()));
				vo.setTitle(entity.getTitle());
				vo.setUser(this.userService.getUser(entity.getUserOid()));
				vo.setArea(this.administrativeAreaService.getArea(entity.getAreaOid()));
				vos.add(vo);
			}
			PageInfo<NewsColumnVO> pageInfo = new PageInfo<>();
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
	public List<NewsColumnVO> newsColumnTreeview(HttpServletRequest request) {
		UserVO user = this.userService.getUser(request.getSession().getAttribute("user").toString());
		NewsColumnEntity params = new NewsColumnEntity();
		List<String> areaOids = this.administrativeAreaService.getChildOids(user.getArea().getOid());
		if (!CollectionUtils.isEmpty(areaOids)) {
			params.setAreaOids(areaOids);
		}
		List<NewsColumnEntity> list = this.newsColumnMapper.selectParentNewsColumns(params);
		if (!CollectionUtils.isEmpty(list)) {
			List<NewsColumnVO> vos = new ArrayList<>();
			for (NewsColumnEntity entity : list) {
				NewsColumnVO vo = new NewsColumnVO();
				vo.setOid(entity.getOid());
				vo.setTitle(entity.getTitle());
				vo.setParent(this.getNewsColumn(entity.getPOid()));
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
	public List<String> getNewsColumnOidsByArea(String areaOid) {
		return this.newsColumnMapper.selectNewsColumnOidsByArea(areaOid);
	}

	@Override
	public List<String> getNewsColumnOidsByParent(String parentOid) {
		return this.newsColumnMapper.selectNewsColumnOidsByParent(parentOid);
	}
	
	
}
