package com.sjtc.weiwen.msgs.dao.entity;

import java.io.Serializable;
import java.util.Date;

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
public class MsgsEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 491504284096928041L;

	private String oid;

    private String senderOid;

    private String sender;

    private String receiverOid;

    private String receiver;

    private String title;

    private String msgType;

    private String content;

    private String state;

    private Date insertTime;

    private Date readTime;
    
    private Date startTime;
    
    private Date endTime;
}