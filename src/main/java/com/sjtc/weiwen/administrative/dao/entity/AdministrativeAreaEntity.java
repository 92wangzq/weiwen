package com.sjtc.weiwen.administrative.dao.entity;

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
public class AdministrativeAreaEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6658463237573193132L;

	private String oid;

    private String title;

    private String pOid;
}