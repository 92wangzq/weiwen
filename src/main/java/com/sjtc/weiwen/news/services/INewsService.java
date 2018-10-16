package com.sjtc.weiwen.news.services;

import javax.servlet.http.HttpServletRequest;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.news.controllers.form.NewsVO;

public interface INewsService {

	BaseResult save(NewsVO news, HttpServletRequest req);

	BaseResult delete(String oid);

	PageInfo<NewsVO> getNews(NewsVO news, Integer limit, Integer offset, HttpServletRequest request);

	NewsVO getNews(String oid);

}
