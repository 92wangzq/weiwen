package com.sjtc.weiwen.user.type.controllers.form;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 248620589521686744L;

	private String oid;

    private String title;
}
