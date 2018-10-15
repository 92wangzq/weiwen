package com.sjtc.weiwen.news.column.services.impl;

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
import com.sjtc.weiwen.news.column.controllers.form.NewsColumnVO;
import com.sjtc.weiwen.news.column.dao.NewsColumnEntityMapper;
import com.sjtc.weiwen.news.column.dao.entity.NewsColumnEntity;
import com.sjtc.weiwen.news.column.services.INewsColumnService;
import com.sjtc.weiwen.user.services.IUserService;

@Service
public class NewsColumnServiceImpl implements INewsColumnService {

	@Autowired
	private NewsColumnEntityMapper newsColumnMapper;
	@Autowired
	private IUserService userService;

	@Transactional
	@Override
	public BaseResult save(NewsColumnVO newsColumn, HttpServletRequest req) {
		NewsColumnEntity entity = new NewsColumnEntity();
		if (StringUtil.isEmpty(newsColumn.getOid())) {
			entity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
			entity.setTitle(newsColumn.getTitle());
			entity.setPOid(newsColumn.getPOid());
			entity.setUserOid(req.getSession().getAttribute("user").toString());
			this.newsColumnMapper.insert(entity);
		} else {
			entity.setOid(newsColumn.getOid());
			entity.setTitle(newsColumn.getTitle());
			entity.setPOid(newsColumn.getPOid());
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
	public PageInfo<NewsColumnVO> getNewsColumns(NewsColumnVO newsColumn, Integer limit, Integer offset) {
		NewsColumnEntity params = new NewsColumnEntity();
		params.setOid(newsColumn.getOid());
		params.setTitle(newsColumn.getTitle());
		params.setPOid(newsColumn.getPOid());
		Page<NewsColumnEntity> page = PageHelper.startPage(offset, limit, true);
		List<NewsColumnEntity> list = this.newsColumnMapper.selectNewsColumnsByFuzz(params);
		if (list != null && list.size() > 0) {
			List<NewsColumnVO> vos = new ArrayList<>();
			for (NewsColumnEntity entity : list) {
				NewsColumnVO vo = new NewsColumnVO();
				vo.setOid(entity.getOid());
				vo.setPOid(entity.getPOid());
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
				vo.setPOid(entity.getPOid());
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
			vo.setPOid(entity.getPOid());
			vo.setUser(this.userService.getUser(entity.getUserOid()));
			return vo;
		}
		return null;
	}
	
	
}
