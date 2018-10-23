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
public class DictionaryDataVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8003123802647928529L;

	private String oid;

    private DictionaryVO dictionary;

    private DictionaryDataVO parent;

    private String dictDataName;

    private String dictDataCode;

    private String state;
}
