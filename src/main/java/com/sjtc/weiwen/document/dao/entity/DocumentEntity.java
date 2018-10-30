package com.sjtc.weiwen.document.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class DocumentEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1312660524000590989L;

	private String oid;

    private String documentNumber;
    
    private String areaOid;

    private String fileOid;

    private String columnOid;

    private String userOid;

    private String description;

    private String title;

    private Integer downloadNum;

    private String remarks;
    
    private String state;

    private Date insertTime;

    private Date updateTime;
    
    
    private Date startTime;
    
    private Date endTime;
    
    private List<String> oids;
    
    private List<String> areaOids;
    
    private String taskId;
    
    private String assignee;
    
}