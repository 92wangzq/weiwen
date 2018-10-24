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
import com.sjtc.weiwen.msgs.controllers.form.MsgChildVO;
import com.sjtc.weiwen.msgs.controllers.form.MsgParentVO;
import com.sjtc.weiwen.msgs.controllers.form.MsgVO;
import com.sjtc.weiwen.msgs.dao.MsgsChildEntityMapper;
import com.sjtc.weiwen.msgs.dao.MsgsEntityMapper;
import com.sjtc.weiwen.msgs.dao.MsgsParentEntityMapper;
import com.sjtc.weiwen.msgs.dao.entity.MsgsChildEntity;
import com.sjtc.weiwen.msgs.dao.entity.MsgsEntity;
import com.sjtc.weiwen.msgs.dao.entity.MsgsParentEntity;
import com.sjtc.weiwen.msgs.services.IMsgService;
import com.sjtc.weiwen.user.controllers.form.UserVO;
import com.sjtc.weiwen.user.services.IUserService;

@Service
public class MsgServiceImpl implements IMsgService {

	@Autowired
	private MsgsEntityMapper msgMapper;
	@Autowired
	private MsgsParentEntityMapper msgsParentMapper;
	@Autowired
	private MsgsChildEntityMapper msgsChildMapper;
	@Autowired
	private IUserService userService;

	@Transactional
	@Override
	public BaseResult save(MsgVO vo) {
		String receiverOids[] = vo.getReceiverOids().split(",");
		String string = JSON.toJSON(SecurityUtils.getSubject().getPrincipal()).toString();
		UserVO user = JSON.parseObject(string, UserVO.class);
		MsgsParentEntity parentEntity = new MsgsParentEntity();
		parentEntity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
		parentEntity.setTitle(vo.getTitle());
		parentEntity.setSender(user.getRealName());
		parentEntity.setSenderOid(user.getOid());
		parentEntity.setInsertTime(new Date());
		this.msgsParentMapper.insert(parentEntity);
		for (String receiverOid : receiverOids) {
			MsgsChildEntity childEntity = new MsgsChildEntity();
			childEntity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
			childEntity.setParentOid(parentEntity.getOid());
			childEntity.setSenderOid(user.getOid());
			childEntity.setSender(user.getRealName());
			childEntity.setReceiverOid(receiverOid);
			childEntity.setReceiver(this.userService.getUser(receiverOid).getRealName());
			childEntity.setTitle(vo.getTitle());
			childEntity.setMsgType(vo.getMsgType());
			childEntity.setContent(vo.getContent());
			childEntity.setState("0");
			childEntity.setInsertTime(new Date());
			this.msgsChildMapper.insert(childEntity);
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
	public PageInfo<MsgParentVO> getSenderMsgs(MsgVO msg, Integer limit, Integer offset) {
		MsgsParentEntity params = new MsgsParentEntity();
		String string = JSON.toJSON(SecurityUtils.getSubject().getPrincipal()).toString();
		UserVO user = JSON.parseObject(string, UserVO.class);
		params.setSenderOid(user.getOid());
		params.setTitle(msg.getTitle());
		params.setStartTime(msg.getStartTime());
		params.setEndTime(msg.getEndTime());
		Page<MsgsParentEntity> page = PageHelper.startPage(offset, limit, true);
		List<MsgsParentEntity> list = this.msgsParentMapper.selectMsgsByFuzz(params);
		if (!CollectionUtils.isEmpty(list)) {
			List<MsgParentVO> vos = new ArrayList<>();
			for (MsgsParentEntity entity : list) {
				MsgParentVO vo = new MsgParentVO();
				vo.setOid(entity.getOid());
				vo.setSender(this.userService.getUser(entity.getSenderOid()));
				vo.setTitle(entity.getTitle());
				vo.setInsertTime(entity.getInsertTime());
				vo.setChilds(this.getMsgChilds(entity.getOid()));
				vos.add(vo);
			}
			PageInfo<MsgParentVO> pageInfo = new PageInfo<>();
			pageInfo.setPageNum(page.getPageNum());
			pageInfo.setPageSize(page.getPageSize());
			pageInfo.setPages(page.getPages());
			pageInfo.setTotal(page.getTotal());
			pageInfo.setRows(vos);
			return pageInfo;
		}
		return null;
	}

	private List<MsgChildVO> getMsgChilds(String oid) {
		List<MsgsChildEntity> list = this.msgsChildMapper.selectMsgsChildsByParent(oid);
		if (!CollectionUtils.isEmpty(list)) {
			List<MsgChildVO> vos = new ArrayList<>();
			for (MsgsChildEntity childEntity : list) {
				MsgChildVO vo = new MsgChildVO();
				vo.setOid(childEntity.getOid());
				vo.setSenderUser(this.userService.getUser(childEntity.getSenderOid()));
				vo.setSender(childEntity.getSender());
				vo.setReceiverUser(this.userService.getUser(childEntity.getReceiverOid()));
				vo.setReceiver(childEntity.getReceiver());
				vo.setTitle(childEntity.getTitle());
				vo.setMsgType(childEntity.getMsgType());
				vo.setContent(childEntity.getContent());
				vo.setState(childEntity.getState());
				vo.setInsertTime(childEntity.getInsertTime());
				vo.setReadTime(childEntity.getReadTime());
				vos.add(vo);
			}
			return vos;
		}
		return null;
	}

	@Override
	public PageInfo<MsgChildVO> getReceiverMsgs(MsgVO msg, Integer limit, Integer offset) {
		MsgsChildEntity params = new MsgsChildEntity();
		String string = JSON.toJSON(SecurityUtils.getSubject().getPrincipal()).toString();
		UserVO user = JSON.parseObject(string, UserVO.class);
		params.setReceiverOid(user.getOid());
		params.setTitle(msg.getTitle());
		params.setStartTime(msg.getStartTime());
		params.setEndTime(msg.getEndTime());
		Page<MsgsChildEntity> page = PageHelper.startPage(offset, limit, true);
		List<MsgsChildEntity> list = this.msgsChildMapper.selectMsgsByFuzz(params);
		if (!CollectionUtils.isEmpty(list)) {
			List<MsgChildVO> vos = new ArrayList<>();
			for (MsgsChildEntity childEntity : list) {
				MsgChildVO vo = new MsgChildVO();
				vo.setOid(childEntity.getOid());
				vo.setSenderUser(this.userService.getUser(childEntity.getSenderOid()));
				vo.setSender(childEntity.getSender());
				vo.setReceiverUser(this.userService.getUser(childEntity.getReceiverOid()));
				vo.setReceiver(childEntity.getReceiver());
				vo.setTitle(childEntity.getTitle());
				vo.setMsgType(childEntity.getMsgType());
				vo.setContent(childEntity.getContent());
				vo.setState(childEntity.getState());
				vo.setInsertTime(childEntity.getInsertTime());
				vo.setReadTime(childEntity.getReadTime());
				vos.add(vo);
			}
			PageInfo<MsgChildVO> pageInfo = new PageInfo<>();
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
	public BaseResult deleteParent(String oid) {
		this.msgsParentMapper.deleteByPrimaryKey(oid);
		return new BaseResult();
	}

	@Transactional
	@Override
	public BaseResult deleteChild(String oid) {
		MsgsChildEntity childEntity = this.msgsChildMapper.selectByPrimaryKey(oid);
		if (childEntity != null) {
			childEntity.setState("1");
			this.msgsChildMapper.updateByPrimaryKeySelective(childEntity);
		}
		return new BaseResult();
	}

	@Override
	public MsgChildVO getMsg(String oid) {
		MsgsChildEntity entity = this.msgsChildMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			MsgChildVO vo = new MsgChildVO();
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
		MsgsChildEntity entity = this.msgsChildMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			entity.setState("2");
			entity.setReadTime(new Date());
			this.msgsChildMapper.updateByPrimaryKeySelective(entity);
		}
		return new BaseResult();
	}

	@Override
	public Integer getUnreadMsgCount() {
		String userStr = JSON.toJSON(SecurityUtils.getSubject().getPrincipal()).toString();
		UserVO user = JSON.parseObject(userStr, UserVO.class);
		Integer result = this.msgsChildMapper.selectUnreadMsgCountByUser(user.getOid());
		return result;
	}
}
