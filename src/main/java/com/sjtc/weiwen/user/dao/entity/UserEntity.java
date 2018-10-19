package com.sjtc.weiwen.user.dao.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author wangzhiqiang
 *
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1373673568046507843L;

	/**
	 * 删除
	 */
	public static final String STATE_DEL = "Del";

	/**
	 * 冻结
	 */
	public static final String STATE_FROZEN = "Frozen";

	/**
	 * 正常
	 */
	public static final String STATE_NORMAL = "Normal";

	private String oid;

	/**
	 * 用户类型ID
	 */
	private String userTypeOid;

	/**
	 * 行政区域ID
	 */
	private String areaOid;

	/**
	 * 真是姓名
	 */
	private String realName;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 用户密码
	 */
	private String userPwd;

	/**
	 * 盐值
	 */
	private String salt;

	/**
	 * 状态
	 */
	private String state;

	/**
	 * 创建时间
	 */
	private Date insertTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 密码盐. 重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
	 * 
	 * @return
	 */
	public String getCredentialsSalt() {
		return this.userName + this.salt;
	}
}