package com.sjtc.util;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author wangzhiqiang
 *
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3848351217835192655L;

	private String code="0";
	
	private String message="SUCCESS";
	
	private Serializable token;
}