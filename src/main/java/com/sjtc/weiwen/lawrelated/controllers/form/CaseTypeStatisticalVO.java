package com.sjtc.weiwen.lawrelated.controllers.form;

import java.io.Serializable;

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
public class CaseTypeStatisticalVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2055987068695160382L;

	private String month;
	
	private Integer xz;
	
	private Integer xs;
	
	private Integer ms;
    
    //返回结果
    private String label;
    
    private Float value;
}
