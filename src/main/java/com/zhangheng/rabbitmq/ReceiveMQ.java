package com.zhangheng.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class ReceiveMQ {

	public static String receive() throws IOException, TimeoutException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("54.8.141.51");
		factory.setPort(5672);
		factory.setUsername("root");
		factory.setPassword("root");
		
		Connection connection= factory.newConnection();
		Channel channel = connection.createChannel();
		/*channel.basicQos(64);
		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("message :"+new String(body));
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};*/
//		return channel.basicConsume("queue_demo123",false,"mytag", consumer);
//		return channel.basicConsume("queue_demo123", consumer);
		return channel.basicGet("queue_demo123", true).toString();
	}
}
