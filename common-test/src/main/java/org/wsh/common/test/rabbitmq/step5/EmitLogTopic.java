package org.wsh.common.test.rabbitmq.step5;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.UUID;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  发送端(发送不同的选择键)
 * since Date： 2016-11-10 16:44
 */
public class EmitLogTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception
    {
        // 创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String[] routing_keys = new String[] { "kernal.info", "cron.warning",
                "auth.info", "kernel.critical" };
        for (String routing_key : routing_keys)
        {
            String msg = UUID.randomUUID().toString();
            channel.basicPublish(EXCHANGE_NAME, routing_key, null, msg
                    .getBytes());
            System.out.println(" [x] Sent routingKey = "+routing_key+" ,msg = " + msg + ".");
        }

        channel.close();
        connection.close();
    }
}
