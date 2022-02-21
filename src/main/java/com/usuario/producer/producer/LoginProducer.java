package com.usuario.producer.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.usuario.producer.controller.dto.LoginDTO;

@Component
public class LoginProducer {

	private String logarTopic;
	private KafkaTemplate<String, LoginDTO> kafkaLoginTemplate;

	public LoginProducer(@Value("${login.topic.name}") String logarTopic,
			KafkaTemplate<String, LoginDTO> kafkaLoginTemplate) {

		this.logarTopic = logarTopic;
		this.kafkaLoginTemplate = kafkaLoginTemplate;
	}
	
	public void sendLogar(LoginDTO login) {
		kafkaLoginTemplate.send(logarTopic, login);
	}
}
