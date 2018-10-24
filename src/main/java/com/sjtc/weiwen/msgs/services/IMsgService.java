package com.sjtc.weiwen.msgs.services;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.msgs.controllers.form.MsgChildVO;
import com.sjtc.weiwen.msgs.controllers.form.MsgParentVO;
import com.sjtc.weiwen.msgs.controllers.form.MsgVO;

public interface IMsgService {

	BaseResult save(MsgVO vo);

	/**
	 * 消息列表
	 * @param limit
	 * @param offset
	 * @return
	 */
	PageInfo<MsgVO> getMsgs(MsgVO vo, Integer limit, Integer offset);

	/**
	 * 获取当前登录人已发送消息列表
	 * @param vo
	 * @param limit
	 * @param offset
	 * @return
	 */
	PageInfo<MsgParentVO> getSenderMsgs(MsgVO vo, Integer limit, Integer offset);

	/**
	 * 获取当前登录人接收消息列表
	 * @param vo
	 * @param limit
	 * @param offset
	 * @return
	 */
	PageInfo<MsgChildVO> getReceiverMsgs(MsgVO vo, Integer limit, Integer offset);

	/**
	 * 逻辑删除消息
	 * @param oid
	 * @return
	 */
	BaseResult deleteParent(String oid);
	
	/**
	 * 逻辑删除消息
	 * @param oid
	 * @return
	 */
	BaseResult deleteChild(String oid);

	/**
	 * 消息详情
	 * @param oid
	 * @return
	 */
	MsgChildVO getMsg(String oid);

	/**
	 * 标识消息为已读
	 * @param oid
	 * @return
	 */
	BaseResult read(String oid);

	/**
	 * 未读消息数量
	 * @return
	 */
	Integer getUnreadMsgCount();

}
