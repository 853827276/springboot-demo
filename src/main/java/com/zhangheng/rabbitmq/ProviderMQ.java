package com.zhangheng.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class ProviderMQ {

	private static final String host ="54.8.141.51";
	private static final int port =15672;
	
	public static void sendMQ(String message) throws IOException, TimeoutException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setPort(port);
		factory.setUsername("root");
		factory.setPassword("root");
		
		Connection connection= factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare("exchange_demo123", "direct",true,false,null);//声明一个交换机
		channel.queueDeclare("queue_demo123", true, false,false,null);//声明一个队列（持久化/非排他 非自动删除）
		channel.queueBind("queue_demo123", "exchange_demo123", "demo_key");//绑定
		
		channel.basicPublish("exchange_demo123", "demo_key", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
		
		channel.close();
		connection.close();
	}
}
