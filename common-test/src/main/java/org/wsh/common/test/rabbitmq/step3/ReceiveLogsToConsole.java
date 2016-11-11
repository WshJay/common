package org.wsh.common.test.rabbitmq.step3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.util.concurrent.TimeoutException;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  随机创建一个队列，然后将队列与转发器绑定，然后将消费者与该队列绑定，然后打印到控制台。
 * since Date： 2016-11-10 16:34
 */
public class ReceiveLogsToConsole {

    private final static String EXCHANGE_NAME = "ex_log";

    public static void main(String[] argv) throws java.io.IOException,
            java.lang.InterruptedException, TimeoutException {
        // 创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // 创建一个非持久的、唯一的且自动删除的队列
        String queueName = channel.queueDeclare().getQueue();
        // 为转发器指定队列，设置binding
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 指定接收者，第二个参数为自动应答，无需手动应答
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");

        }

    }
}
