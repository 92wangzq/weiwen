package com.sjtc.weiwen.user.type.dao.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1289792952124734551L;

	private String oid;

    private String title;
}