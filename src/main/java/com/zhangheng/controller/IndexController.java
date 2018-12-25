package com.zhangheng.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhangheng.rabbitmq.ProviderMQ;
import com.zhangheng.rabbitmq.ReceiveMQ;

import io.swagger.annotations.ApiOperation;

@Controller
public class IndexController {
	@Autowired
	private JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	private String emailFrom;

	/**
	 * 首页
	 * @author zhangh
	 * @date 2018年10月10日下午4:15:14
	 * @return
	 */
	@ApiOperation(value = "首页接口", notes = "首页接口文档详情")
	@RequestMapping("/index")
	public String index() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailFrom);
		message.setTo(emailFrom);
		message.setSubject("this is a subject");
		message.setText("this is a text");
		mailSender.send(message);
		return "index";
	}

	/**
	 * 发送消息
	 * @author zhangh
	 * @date 2018年10月10日下午4:15:22
	 * @param message
	 * @throws IOException
	 * @throws TimeoutException
	 */
	@ApiOperation(value = "MQ 发送接口", notes = "发送消息")
	@PostMapping("/sendMQ")
	public void sendMessage(String message) throws IOException, TimeoutException {
		ProviderMQ.sendMQ(message);
	}

	/**
	 * 消费消息
	 * @author zhangh
	 * @date 2018年10月10日下午4:15:30
	 * @throws IOException
	 * @throws TimeoutException
	 */
	@ApiOperation(value = "MQ 接受消息", notes = "消费消息")
	@RequestMapping("/receiveMQ")
	public void receiveMQ() throws IOException, TimeoutException {
		String str = ReceiveMQ.receive();
		System.out.println("str=" + str);
	}
}
