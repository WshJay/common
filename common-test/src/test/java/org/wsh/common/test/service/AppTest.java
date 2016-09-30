package org.wsh.common.test.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTest {
	
	private final Logger log = LoggerFactory.getLogger(TestOrderService.class);
	
	private static String appid = "wx04d5c2e2d91dc5dc";
	
	private static String mch_id = "1277425501";
	
	private static String device_info = "WEB";
	
	private static String nonce_str = "5K8264ILTKCH16CQ2502SI8ZNMTM67VS";
	
	

	@Test
	public void test(){
		
		String demo = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";
		String companyUrl = "http://bizjk-test.duoju.info/login.do";
		try {
			String comURL = URLEncoder.encode(companyUrl, "UTF-8");
			StringBuffer weiXinUrl = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
			weiXinUrl.append(appid).append("&redirect_uri=").append(comURL).append("&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect");
			log.info(weiXinUrl.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
}

