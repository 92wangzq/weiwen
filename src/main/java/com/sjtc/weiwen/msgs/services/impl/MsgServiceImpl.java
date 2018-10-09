package com.sjtc.weiwen.msgs.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.msgs.controllers.form.MsgVO;
import com.sjtc.weiwen.msgs.dao.MsgsEntityMapper;
import com.sjtc.weiwen.msgs.dao.entity.MsgsEntity;
import com.sjtc.weiwen.msgs.services.IMsgService;
import com.sjtc.weiwen.user.controllers.form.UserVO;
import com.sjtc.weiwen.user.services.IUserService;

@Service
public class MsgServiceImpl implements IMsgService {

	@Autowired
	private MsgsEntityMapper msgMapper;
	@Autowired
	private IUserService userService;

	@Transactional
	@Override
	public BaseResult save(MsgVO vo) {
		String receiverOids[] = vo.getReceiverOids().split(",");
		for (String receiverOid : receiverOids) {
			String string = JSON.toJSON(SecurityUtils.getSubject().getPrincipal()).toString();
			System.out.println(string);
			UserVO user = JSON.parseObject(string, UserVO.class);
			MsgsEntity entity = new MsgsEntity();
			entity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
			entity.setSenderOid(user.getOid());
			entity.setSender(user.getRealName());
			entity.setReceiverOid(receiverOid);
			entity.setReceiver(this.userService.getUser(receiverOid).getRealName());
			entity.setTitle(vo.getTitle());
			entity.setMsgType(vo.getMsgType());
			entity.setContent(vo.getContent());
			entity.setState("0");
			entity.setInsertTime(new Date());
			this.msgMapper.insert(entity);
		}
		return new BaseResult();
	}

	@Override
	public PageInfo<MsgVO> getMsgs(MsgVO msg, Integer limit, Integer offset) {
		MsgsEntity params = new MsgsEntity();
//		params.setOid(msg.getOid());
//		params.setSenderOid(msg.getSenderUser().getOid());
//		params.setSender(msg.getSender());
//		params.setReceiverOid(msg.getReceiverUser().getOid());
//		params.setReceiver(msg.getReceiver());
//		params.setMsgType(msg.getMsgType());
//		params.setContent(msg.getContent());
//		params.setState("0");
//		params.setStartTime(msg.getStartTime());
//		params.setEndTime(msg.getEndTime());
		Page<MsgsEntity> page = PageHelper.startPage(offset, limit, true);
		List<MsgsEntity> list = this.msgMapper.selectMsgsByFuzz(params);
		if (!CollectionUtils.isEmpty(list)) {
			List<MsgVO> vos = new ArrayList<>();
			for (MsgsEntity entity : list) {
				MsgVO vo = new MsgVO();
				vo.setOid(entity.getOid());
				vo.setSenderUser(this.userService.getUser(entity.getSenderOid()));
				vo.setSender(entity.getSender());
				vo.setReceiverUser(this.userService.getUser(entity.getReceiverOid()));
				vo.setReceiver(entity.getReceiver());
				vo.setTitle(entity.getTitle());
				vo.setMsgType(entity.getMsgType());
				vo.setContent(entity.getContent());
				vo.setState(entity.getState());
				vo.setInsertTime(entity.getInsertTime());
				vo.setReadTime(entity.getReadTime());
				vos.add(vo);
			}
			PageInfo<MsgVO> pageInfo = new PageInfo<>();
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
	public PageInfo<MsgVO> getSenderMsgs(MsgVO msg, Integer limit, Integer offset) {
		MsgsEntity params = new MsgsEntity();
		String string = JSON.toJSON(SecurityUtils.getSubject().getPrincipal()).toString();
		System.out.println(string);
		UserVO user = JSON.parseObject(string, UserVO.class);
		params.setSenderOid(user.getOid());
		params.setTitle(msg.getTitle());
		params.setMsgType(msg.getMsgType());
		params.setContent(msg.getContent());
		params.setState(msg.getState());
		params.setStartTime(msg.getStartTime());
		params.setEndTime(msg.getEndTime());
		Page<MsgsEntity> page = PageHelper.startPage(offset, limit, true);
		List<MsgsEntity> list = this.msgMapper.selectMsgsByFuzz(params);
		if (!CollectionUtils.isEmpty(list)) {
			List<MsgVO> vos = new ArrayList<>();
			for (MsgsEntity entity : list) {
				MsgVO vo = new MsgVO();
				vo.setOid(entity.getOid());
				vo.setSenderUser(this.userService.getUser(entity.getSenderOid()));
				vo.setSender(entity.getSender());
				vo.setReceiverUser(this.userService.getUser(entity.getReceiverOid()));
				vo.setReceiver(entity.getReceiver());
				vo.setTitle(entity.getTitle());
				vo.setMsgType(entity.getMsgType());
				vo.setContent(entity.getContent());
				vo.setState(entity.getState());
				vo.setInsertTime(entity.getInsertTime());
				vo.setReadTime(entity.getReadTime());
				vos.add(vo);
			}
			PageInfo<MsgVO> pageInfo = new PageInfo<>();
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
	public PageInfo<MsgVO> getReceiverMsgs(MsgVO msg, Integer limit, Integer offset) {
		MsgsEntity params = new MsgsEntity();
		String string = JSON.toJSON(SecurityUtils.getSubject().getPrincipal()).toString();
		System.out.println(string);
		UserVO user = JSON.parseObject(string, UserVO.class);
		params.setReceiverOid(user.getOid());
		params.setTitle(msg.getTitle());
		params.setMsgType(msg.getMsgType());
		params.setContent(msg.getContent());
		params.setState(msg.getState());
		params.setStartTime(msg.getStartTime());
		params.setEndTime(msg.getEndTime());
		Page<MsgsEntity> page = PageHelper.startPage(offset, limit, true);
		List<MsgsEntity> list = this.msgMapper.selectMsgsByFuzz(params);
		if (!CollectionUtils.isEmpty(list)) {
			List<MsgVO> vos = new ArrayList<>();
			for (MsgsEntity entity : list) {
				MsgVO vo = new MsgVO();
				vo.setOid(entity.getOid());
				vo.setSenderUser(this.userService.getUser(entity.getSenderOid()));
				vo.setSender(entity.getSender());
				vo.setReceiverUser(this.userService.getUser(entity.getReceiverOid()));
				vo.setReceiver(entity.getReceiver());
				vo.setTitle(entity.getTitle());
				vo.setMsgType(entity.getMsgType());
				vo.setContent(entity.getContent());
				vo.setState(entity.getState());
				vo.setInsertTime(entity.getInsertTime());
				vo.setReadTime(entity.getReadTime());
				vos.add(vo);
			}
			PageInfo<MsgVO> pageInfo = new PageInfo<>();
			pageInfo.setPageNum(page.getPageNum());
			pageInfo.setPageSize(page.getPageSize());
			pageInfo.setPages(page.getPages());
			pageInfo.setTotal(page.getTotal());
			pageInfo.setRows(vos);
			return pageInfo;
		}
		return null;
	}

	@Transactional
	@Override
	public BaseResult delete(String oid) {
		MsgsEntity entity = this.msgMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			entity.setState("1");
			this.msgMapper.updateByPrimaryKeySelective(entity);
		}
		return null;
	}

	@Override
	public MsgVO getMsg(String oid) {
		MsgsEntity entity = this.msgMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			MsgVO vo = new MsgVO();
			vo.setOid(entity.getOid());
			vo.setSenderUser(this.userService.getUser(entity.getSenderOid()));
			vo.setSender(entity.getSender());
			vo.setReceiverUser(this.userService.getUser(entity.getReceiverOid()));
			vo.setReceiver(entity.getReceiver());
			vo.setTitle(entity.getTitle());
			vo.setMsgType(entity.getMsgType());
			vo.setContent(entity.getContent());
			vo.setState(entity.getState());
			vo.setInsertTime(entity.getInsertTime());
			vo.setReadTime(entity.getReadTime());
			return vo;
		}
		return null;
	}

	@Transactional
	@Override
	public BaseResult read(String oid) {
		MsgsEntity entity = this.msgMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			entity.setState("2");
			entity.setReadTime(new Date());
			this.msgMapper.updateByPrimaryKeySelective(entity);
		}
		return new BaseResult();
	}
}
