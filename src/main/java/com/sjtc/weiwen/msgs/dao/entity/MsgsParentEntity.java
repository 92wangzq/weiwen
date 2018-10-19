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
public class MsgsParentEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 14998604082398485L;

	private String oid;

    private String title;

    private String sender;

    private String senderOid;

    private Date insertTime;

    //
    private Date startTime;
    
    private Date endTime;
}