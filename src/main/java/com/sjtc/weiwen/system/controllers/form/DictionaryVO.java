package com.sjtc.weiwen.system.controllers.form;

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
public class DictionaryVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2545314723573974227L;

	private String oid;

    private String dictName;

    private String dictValue;
}
