package com.sjtc.weiwen.news.dao.entity;

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
public class NewsEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6623853928618825001L;

	private String oid;

    private String userOid;

    private String columnOid;

    private String title;

    private String fileOid;

    private Date insertTime;

    private String content;
    
    private Date startTime;
    
    private Date endTime;
}