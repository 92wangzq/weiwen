package com.sjtc.weiwen.news.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.sjtc.weiwen.news.controllers.form.NewsVO;
import com.sjtc.weiwen.news.services.INewsService;

@RestController
@RequestMapping(value="/news", produces="application/json")
public class NewsController {

	@Autowired
	private INewsService newsService;
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResult> save(NewsVO news, HttpServletRequest req) {
		return new ResponseEntity<BaseResult>(this.newsService.save(news, req), HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResult> remove(@RequestParam(name="oid")String oid) {
		return new ResponseEntity<BaseResult>(this.newsService.delete(oid), HttpStatus.OK);
	}
	
	@RequiresPermissions("news:list")
	@RequestMapping(value="/searchNews", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<PageInfo<NewsVO>> searchNews(NewsVO news, @RequestParam(name="limit", required=false)Integer limit, @RequestParam(name="offset", required=false)Integer offset, HttpServletRequest request) {
		if (limit == null || limit == 0) {
			limit = 1;
		}
		if (offset == null || offset == 0) {
			offset = 10;
		}
		return new ResponseEntity<PageInfo<NewsVO>>(this.newsService.getNews(news, limit, offset, request), HttpStatus.OK);
	}
	
	@RequestMapping(value="/viewNews", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<NewsVO> viewNews(@RequestParam(name="oid")String oid) {
		return new ResponseEntity<NewsVO>(this.newsService.getNews(oid), HttpStatus.OK);
	}
	
}
