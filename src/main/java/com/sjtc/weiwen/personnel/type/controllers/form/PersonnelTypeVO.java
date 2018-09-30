package com.sjtc.weiwen.personnel.type.controllers.form;

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
public class PersonnelTypeVO {

	private String oid;
	
	private String title;
	
	private PersonnelTypeVO type;
}
