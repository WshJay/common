package org.wsh.common.rest.action.message;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.model.msg.MessageDO;
import org.wsh.common.rest.socket.SystemWebSocketHandler;
import org.wsh.common.service.api.UserService;
import org.wsh.common.support.exception.BusinessException;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;


@Controller
@RequestMapping("/msg")
public class MessageAction {

	@Resource
	private SystemWebSocketHandler handler;

	@Resource
	private UserService userService;

	// 跳转到交谈聊天页面
	@RequestMapping(value = "talk", method = RequestMethod.GET)
	public String talk(Model model, Long toUserId) {
		try {
			if (toUserId != null){
				UserBasicDO toUser = userService.getUserByUserId(toUserId);
				if(toUser != null){
					model.addAttribute("toUserId",toUserId);
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return "msg/talk";
	}

	// 跳转到发布广播页面
	@RequestMapping(value = "broadcast", method = RequestMethod.GET)
	public String broadcast() {
		return "/msg/broadcast";
	}

	// 发布系统广播（群发）
	@ResponseBody
	@RequestMapping(value = "broadcast", method = RequestMethod.POST)
	public void broadcast(String text, Long formUserId) throws IOException {
		MessageDO messageDO = new MessageDO();
		messageDO.setGmtCreated(new Date());
		messageDO.setFromUserId(String.valueOf(formUserId));
		messageDO.setContent(text);
		handler.broadcast(new TextMessage(JSONObject.toJSONString(messageDO,
				SerializerFeature.WriteDateUseDateFormat)));
	}
}