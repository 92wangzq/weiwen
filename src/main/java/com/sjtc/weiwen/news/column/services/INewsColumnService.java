package com.sjtc.weiwen.news.column.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.news.column.controllers.form.NewsColumnVO;

public interface INewsColumnService {

	BaseResult save(NewsColumnVO newsColumn, HttpServletRequest req);

	BaseResult delete(String oid);

	PageInfo<NewsColumnVO> getNewsColumns(NewsColumnVO newsColumn, Integer limit, Integer offset, HttpServletRequest request);

	List<NewsColumnVO> getChildrens(String pOid);

	NewsColumnVO getNewsColumn(String oid);

	/**
	 * 获取一级栏目
	 * @return
	 */
	PageInfo<NewsColumnVO> getParentNewsColumns(NewsColumnVO newsColumn, Integer limit, Integer offset, HttpServletRequest request);

	/**
	 * 获取树形栏目
	 * @param request
	 * @return
	 */
	List<NewsColumnVO> newsColumnTreeview(HttpServletRequest request);
	
	/**
	 * 根据所属区域id获取栏目oids
	 * @param areaOid
	 * @return
	 */
	List<String> getNewsColumnOidsByArea(String areaOid);

	/**
	 * 根据父级oid获取所有子级oid
	 * @param oid
	 * @return
	 */
	List<String> getNewsColumnOidsByParent(String oid);

}
