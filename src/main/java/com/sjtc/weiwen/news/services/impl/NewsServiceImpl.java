package com.sjtc.weiwen.news.services.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.sjtc.weiwen.news.column.services.INewsColumnService;
import com.sjtc.weiwen.news.controllers.form.NewsVO;
import com.sjtc.weiwen.news.dao.NewsEntityMapper;
import com.sjtc.weiwen.news.dao.entity.NewsEntity;
import com.sjtc.weiwen.news.services.INewsService;
import com.sjtc.weiwen.user.controllers.form.UserVO;
import com.sjtc.weiwen.user.services.IUserService;

@Service
public class NewsServiceImpl implements INewsService {

	@Autowired
	private NewsEntityMapper newsMapper;
	@Autowired
	private IUserService userService;
	@Autowired
	private INewsColumnService newsColumnService;

	@Transactional
	@Override
	public BaseResult save(NewsVO news, HttpServletRequest req) {
		if (StringUtil.isEmpty(news.getOid())) {
			NewsEntity entity = new NewsEntity();
			entity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
			entity.setTitle(news.getTitle());
			entity.setContent(news.getContent());
			entity.setInsertTime(new Date());
			entity.setUserOid(req.getSession().getAttribute("user").toString());
			entity.setColumnOid(news.getColumn().getOid());
			entity.setFileOid(news.getFileOid());
			this.newsMapper.insert(entity);
		} else {
			NewsEntity entity = this.newsMapper.selectByPrimaryKey(news.getOid());
			if (entity == null) {
				return new BaseResult("-1", "news oid not find!", null);
			}
			entity.setTitle(news.getTitle());
			entity.setContent(news.getContent());
			entity.setColumnOid(news.getColumn().getOid());
			entity.setFileOid(news.getFileOid());
			this.newsMapper.updateByPrimaryKeySelective(entity);
		}
		return new BaseResult();
	}

	@Transactional
	@Override
	public BaseResult delete(String oid) {
		this.newsMapper.deleteByPrimaryKey(oid);
		return new BaseResult();
	}

	@Override
	public PageInfo<NewsVO> getNews(NewsVO news, Integer limit, Integer offset, HttpServletRequest request) {
		UserVO user = this.userService.getUser(request.getSession().getAttribute("user").toString());
		NewsEntity params = new NewsEntity();
		List<String> columnOids = new ArrayList<>();
		if (news.getColumn() != null && !StringUtils.isEmpty(news.getColumn().getOid())) {
			columnOids = this.newsColumnService.getNewsColumnOidsByParent(news.getColumn().getOid());
			if (!CollectionUtils.isEmpty(columnOids)) {
				params.setColumnOids(columnOids);
			}
		} else {
			columnOids = this.newsColumnService.getNewsColumnOidsByArea(user.getArea().getOid());
			if (!CollectionUtils.isEmpty(columnOids)) {
				params.setColumnOids(columnOids);
			}
		}
		params.setTitle(news.getTitle());
		params.setStartTime(news.getStartTime());
		params.setEndTime(news.getEndTime());
		Page<NewsEntity> page = PageHelper.startPage(offset, limit, true);
		List<NewsEntity> list = this.newsMapper.selectNewsByFuzz(params);
		if (list != null && list.size() > 0) {
			List<NewsVO> vos = new ArrayList<>();
			for (NewsEntity entity : list) {
				NewsVO vo = new NewsVO();
				vo.setOid(entity.getOid());
				vo.setTitle(entity.getTitle());
				vo.setContent(entity.getContent());
				vo.setInsertTime(entity.getInsertTime());
				vo.setUser(this.userService.getUser(entity.getUserOid()));
				vo.setColumn(this.newsColumnService.getNewsColumn(entity.getColumnOid()));
				vo.setFileOid(entity.getFileOid());
				vos.add(vo);
			}
			PageInfo<NewsVO> pageInfo = new PageInfo<>();
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
	public NewsVO getNews(String oid) {
		NewsEntity entity = this.newsMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			NewsVO vo = new NewsVO();
			vo.setOid(entity.getOid());
			vo.setTitle(entity.getTitle());
			vo.setContent(entity.getContent());
			vo.setInsertTime(entity.getInsertTime());
			vo.setUser(this.userService.getUser(entity.getUserOid()));
			vo.setColumn(this.newsColumnService.getNewsColumn(entity.getColumnOid()));
			vo.setFileOid(entity.getFileOid());
			return vo;
		}
		return null;
	}
}
