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
public class MsgsChildEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4977907858329223699L;

	private String oid;

    private String parentOid;

    private String senderOid;

    private String sender;

    private String receiverOid;

    private String receiver;

    private String title;

    private String msgType;

    private String state;

    private Date insertTime;

    private Date readTime;

    private String content;
    
    //
    private Date startTime;
    
    private Date endTime;
}