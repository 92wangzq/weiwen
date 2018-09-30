package com.sjtc.weiwen.file.controllers.form;

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
public class FileVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6158644648846875506L;

	private String code;
	
	private String message;
	
	private String oid;

    private String title;

    private Integer size;

    private String type;

    private String suffix;

    private String viewPath;

    private String downloadPath;
}
