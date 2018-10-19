package com.sjtc.weiwen.msgs.controllers;

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
import com.sjtc.weiwen.msgs.controllers.form.MsgChildVO;
import com.sjtc.weiwen.msgs.controllers.form.MsgParentVO;
import com.sjtc.weiwen.msgs.controllers.form.MsgVO;
import com.sjtc.weiwen.msgs.services.IMsgService;

@RestController
@RequestMapping(value = "/msg", produces = "application/json")
public class MsgController {

	@Autowired
	private IMsgService msgService;

	@RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public ResponseEntity<BaseResult> save(MsgVO vo) {
		return new ResponseEntity<BaseResult>(this.msgService.save(vo), HttpStatus.OK);
	}

	/**
	 * 删除消息
	 * @param oid
	 * @return
	 */
	@RequestMapping(value = "/removeSender", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public ResponseEntity<BaseResult> removeSender(String oid) {
		return new ResponseEntity<BaseResult>(this.msgService.deleteParent(oid), HttpStatus.OK);
	}
	
	/**
	 * 删除消息
	 * @param oid
	 * @return
	 */
	@RequestMapping(value = "/removeReceiver", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public ResponseEntity<BaseResult> removeReceiver(String oid) {
		return new ResponseEntity<BaseResult>(this.msgService.deleteChild(oid), HttpStatus.OK);
	}

	/**
	 * 获取全部消息列表
	 * @param vo
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping(value = "/msgList", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<PageInfo<MsgVO>> msgList(MsgVO vo,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset) {
		return new ResponseEntity<PageInfo<MsgVO>>(this.msgService.getMsgs(vo, limit, offset), HttpStatus.OK);
	}

	/**
	 * 获取发件箱列表
	 * 
	 * @param vo
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping(value = "/msgSenderList", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<PageInfo<MsgParentVO>> msgSenderList(MsgVO vo,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset) {
		return new ResponseEntity<PageInfo<MsgParentVO>>(this.msgService.getSenderMsgs(vo, limit, offset), HttpStatus.OK);
	}

	/**
	 * 获取收件箱列表
	 * @param vo
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping(value = "/msgReceiverList", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<PageInfo<MsgChildVO>> msgReceiverList(MsgVO vo,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset) {
		return new ResponseEntity<PageInfo<MsgChildVO>>(this.msgService.getReceiverMsgs(vo, limit, offset), HttpStatus.OK);
	}
	
	/**
	 * 查看消息详情
	 * @param oid
	 * @return
	 */
	@RequestMapping(value = "/viewMsg", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<MsgChildVO> viewMsg(@RequestParam("oid")String oid) {
		return new ResponseEntity<MsgChildVO>(this.msgService.getMsg(oid), HttpStatus.OK);
	}
	
	/**
	 * 标识消息为已读
	 * @param oid
	 * @return
	 */
	@RequestMapping(value = "/read", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<BaseResult> read(@RequestParam("oid")String oid) {
		return new ResponseEntity<BaseResult>(this.msgService.read(oid), HttpStatus.OK);
	}

}
