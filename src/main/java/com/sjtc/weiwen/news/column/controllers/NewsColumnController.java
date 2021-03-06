package com.sjtc.weiwen.news.column.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.news.column.controllers.form.NewsColumnVO;
import com.sjtc.weiwen.news.column.services.INewsColumnService;

@RestController
@RequestMapping(value="/newsColumn", produces="application/json")
public class NewsColumnController {

	
	@Autowired
	private INewsColumnService newsColumnService;
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResult> save(NewsColumnVO newsColumn, HttpServletRequest req) {
		return new ResponseEntity<BaseResult>(this.newsColumnService.save(newsColumn, req), HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResult> remove(String oid) {
		return new ResponseEntity<BaseResult>(this.newsColumnService.delete(oid), HttpStatus.OK);
	}
	
	/**
	 * 获取栏目列表
	 * @param newsColumn
	 * @param limit
	 * @param offset
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/searchNewsColumns", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<PageInfo<NewsColumnVO>> searchNewsColumns(NewsColumnVO newsColumn, @RequestParam(name="limit", required=false)Integer limit, @RequestParam(name="offset", required=false)Integer offset, HttpServletRequest request) {
		if (limit == null || limit == 0) {
			limit = 1;
		}
		if (offset == null || offset == 0) {
			offset = 10;
		}
		return new ResponseEntity<PageInfo<NewsColumnVO>>(this.newsColumnService.getNewsColumns(newsColumn, limit, offset, request), HttpStatus.OK);
	}
	
	/**
	 * 获取所有一级栏目
	 * @param newsColumn
	 * @param limit
	 * @param offset
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/searchNewsColumnParentList", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<PageInfo<NewsColumnVO>> searchNewsColumnParentList(NewsColumnVO newsColumn, @RequestParam(name="limit", required=false)Integer limit, @RequestParam(name="offset", required=false)Integer offset, HttpServletRequest request) {
		if (limit == null || limit == 0) {
			limit = 1;
		}
		if (offset == null || offset == 0) {
			offset = 10;
		}
		return new ResponseEntity<PageInfo<NewsColumnVO>>(this.newsColumnService.getParentNewsColumns(newsColumn, limit, offset, request), HttpStatus.OK);
	}
	
	/**
	 * 获取树形栏目
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/searchNewsColumnTreeview", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<NewsColumnVO>> searchNewsColumnTreeview(HttpServletRequest request) {
		return new ResponseEntity<List<NewsColumnVO>>(this.newsColumnService.newsColumnTreeview(request), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/children", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<NewsColumnVO>> children(@RequestParam(name="pOid", required=false)String pOid) {
		return new ResponseEntity<List<NewsColumnVO>>(this.newsColumnService.getChildrens(pOid), HttpStatus.OK);
	}
}
