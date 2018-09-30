package com.sjtc.weiwen;

import java.io.Serializable;
import java.util.UUID;

public class BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3422619980476994701L;
	
	@SuppressWarnings("unused")
	private String oid = UUID.randomUUID().toString().replaceAll("-", "");

}
