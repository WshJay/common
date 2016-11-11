package org.wsh.common.test.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.wsh.common.enums.msg.MessageType;
import org.wsh.common.model.msg.MessageDO;

import java.util.Date;

import static com.alibaba.fastjson.JSON.toJSON;
import static org.wsh.common.enums.SessionKey.user;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-04 14:22
 */
public class JsonTransformation {

    public static void main(String[] args) {

        MessageDO messageDO = new MessageDO();
        messageDO.setId(1L);
        messageDO.setContent("abc");
        messageDO.setFromUserId("1");
        messageDO.setType(MessageType.TO_MANY);
        messageDO.setGmtCreated(new Date());

        // 模型转换为String
        String jsonStr = JSONObject.toJSONString(messageDO,
                SerializerFeature.WriteDateUseDateFormat);
        System.out.println(jsonStr);

        // String转换为模型
        MessageDO messageDO1 = JSON.parseObject(jsonStr,new TypeReference<MessageDO>(){});
        System.out.println(messageDO1.getContent());
        System.out.println(messageDO1.getGmtCreated());

    }
}
