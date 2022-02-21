package com.usuario.producer.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class GerarSenhaProducer {
	
	private String gerarSenhaTopic;
	private KafkaTemplate<String, String> kafkaGerarSenhaTemplate;

	public GerarSenhaProducer(@Value("${dynamicPassword.topic.name}") String gerarSenhaTopic,
			KafkaTemplate<String, String> kafkaGerarSenhaTemplate) {

		this.gerarSenhaTopic = gerarSenhaTopic;
		this.kafkaGerarSenhaTemplate = kafkaGerarSenhaTemplate;
	}

	public void sendGerarSenha(String cpf) {
		kafkaGerarSenhaTemplate.send(gerarSenhaTopic, cpf);
	}
}
