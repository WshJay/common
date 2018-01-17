package org.wsh.common.test.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
		
//		String demo = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";
//		String companyUrl = "http://bizjk-test.duoju.info/login.do";
//		try {
//			String comURL = URLEncoder.encode(companyUrl, "UTF-8");
//			StringBuffer weiXinUrl = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
//			weiXinUrl.append(appid).append("&redirect_uri=").append(comURL).append("&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect");
//			log.info(weiXinUrl.toString());
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}

//		for (int i = 26; i < 45; i++) {
//			System.out.println("INSERT INTO file (`name`, `cover_path`, `file_path`, `type`, `user_id`, `tags_id`, `description`, `version`, `is_deleted`, `gmt_created`, `gmt_modified`) VALUES ('MM-" + i + "', 'upload/images/" + i + ".jpg', 'upload/images/" + i + ".jpg', 'IMG', '2', '2', '電影海報!', '0', '0', '2017-02-12 15:50:39', '2017-02-12 15:50:41');");
//		}

		List<String> list = new ArrayList<>();
		list.add("abc");
		String s1 = "abc";
		while (list.contains(s1)){
			s1 = "abc";
		}
		list.add(s1);
		for (String s : list) {
			System.out.println(s);
		}
	}
}

