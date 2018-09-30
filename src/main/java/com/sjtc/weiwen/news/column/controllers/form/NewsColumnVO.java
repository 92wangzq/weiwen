package com.sjtc.weiwen.news.column.controllers.form;

import java.io.Serializable;
import java.util.List;

import com.sjtc.weiwen.user.controllers.form.UserVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class NewsColumnVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7199611090000507695L;

	private String oid;

    private String title;

    private String pOid;

    private UserVO user;
    
    private List<NewsColumnVO> nodes;
    
    private String text;
}
