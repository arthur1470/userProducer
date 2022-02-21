package com.usuario.producer.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.usuario.producer.controller.dto.UsuarioDTO;

@Component
public class RegistroProducer {

	private String registroTopic;
	private KafkaTemplate<String, UsuarioDTO> kafkaUsuarioTemplate;

	public RegistroProducer(@Value("${register.topic.name}") String registroTopic,
			KafkaTemplate<String, UsuarioDTO> kafkaUsuarioTemplate) {

		this.registroTopic = registroTopic;
		this.kafkaUsuarioTemplate = kafkaUsuarioTemplate;
	}

	public void sendRegistrar(UsuarioDTO usuario) {
		kafkaUsuarioTemplate.send(registroTopic, usuario);
	}
}
