package com.sjtc.weiwen.news.column.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.news.column.controllers.form.NewsColumnVO;

public interface INewsColumnService {

	BaseResult save(NewsColumnVO newsColumn, HttpServletRequest req);

	BaseResult delete(String oid);

	PageInfo<NewsColumnVO> getNewsColumns(NewsColumnVO newsColumn, Integer limit, Integer offset);

	List<NewsColumnVO> getChildrens(String pOid);

	NewsColumnVO getNewsColumn(String oid);

}
