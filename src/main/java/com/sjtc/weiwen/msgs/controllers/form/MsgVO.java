package com.sjtc.weiwen.msgs.controllers.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sjtc.weiwen.user.controllers.form.UserVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MsgVO {

	/**
	 * 
	 */
	private String oid;

	/**
	 * 发送人ID
	 */
    private UserVO senderUser;

    /**
     * 发送人名称
     */
    private String sender;

    /**
     * 接收人ID
     */
    private UserVO receiverUser;

    /**
     * 接收人姓名
     */
    private String receiver;
    
    /**
     * 标题
     */
    private String title;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息状态
     */
    private String state;

    /**
     * 发送时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;

    /**
     * 阅读时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date readTime;
    
    /**
     * 收件人id集合
     */
    private String receiverOids;
    
    private Date startTime;
    
    private Date endTime;
}
