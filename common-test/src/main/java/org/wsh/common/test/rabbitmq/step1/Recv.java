package org.wsh.common.test.rabbitmq.step1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-10 13:53
 */
public class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws IOException, InterruptedException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        // 打开连接和创建频道，与发送端一样
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 创建队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        // 指定消费队列  <span style="font-family: Arial, Helvetica, sans-serif;">true表示不用确认即可，只要接收，不管有没有成功处理都会当做成功 false只有当成功处理完毕并确认后才算</span>
        channel.basicConsume(QUEUE_NAME, true, consumer);
        while (true) {
            // nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
            // 返回确认状态 当channel.basicConsume()第二个参数为false时
            // 如果没有这一行确认接收则关闭消费者再次启动还会收到已经接收的消息。。。。
            // 为true时，如果加上这行报错channel error; protocol method: #method<channel.close>(reply-code=406,
            // reply-text=PRECONDITION_FAILED - unknown delivery tag 1,
            // class-id=60, method-id=80)
            // channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
