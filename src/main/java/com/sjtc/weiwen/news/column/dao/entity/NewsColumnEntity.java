package com.sjtc.weiwen.news.column.dao.entity;

import java.io.Serializable;
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
public class NewsColumnEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3969170151352239969L;

	private String oid;

    private String title;

    private String pOid;

    private String userOid;
    
    private String areaOid;
    
    private List<String> areaOids;
}