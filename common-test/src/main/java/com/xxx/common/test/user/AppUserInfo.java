package com.xxx.common.test.user;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.xxx.common.util.date.DateUtil;

import lombok.Getter;
import lombok.Setter;

public class AppUserInfo {

	@Setter
	@Getter
	private String mobile;
	
	@Setter
	@Getter
	private String email;
	
	@Setter
	@Getter
	private String open_id;
	
	/**
	 * 1:QQ 登录用户 2：新浪微博用户  3:腾讯微博 4:微信
	 */
	@Setter
	@Getter
	private int login_type;
	
	@Setter
	@Getter
	private Date last_login_time;
	
	@Setter
	@Getter
	private Date regist_time;
	
	public String getInsertSql(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("INSERT INTO `app_user_info` (phone,email,open_id,login_type,last_login_time)VALUES (");
		if (StringUtils.isBlank(this.mobile)) {
			stringBuffer.append(this.mobile).append(",");
		}else{
			stringBuffer.append("'").append(this.mobile).append("',");
		}
		if (StringUtils.isBlank(this.email)) {
			stringBuffer.append(this.email).append(",");
		}else{
			stringBuffer.append("'").append(this.email).append("',");
		}
		if (StringUtils.isBlank(this.open_id)) {
			stringBuffer.append(this.open_id).append(",");
		}else{
			stringBuffer.append("'").append(this.open_id).append("',");
		}
		stringBuffer.append(this.login_type).append(",'").append(DateUtil.parseDate(this.last_login_time, DateUtil.YYYY_MM_DD_HH_MM_SS)).append("');");
		return stringBuffer.toString();
	}
	
	
}

