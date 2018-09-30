package com.sjtc.weiwen.user.controllers.form;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sjtc.weiwen.administrative.controllers.form.AdministrativeAreaVO;

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
public class UserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5106186541650239293L;

	private String oid;

    private String userTypeOid;

    private String areaOid;

    private String realName;

    private String userName;

    private String userPwd;
    
    private String salt;
    
    private Integer state;
    
    private AdministrativeAreaVO area;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
    /**
	 * 密码盐.
	 * 重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
	 * @return
	 */
	public String getCredentialsSalt() {
		return this.userName + this.salt;
	}
}
