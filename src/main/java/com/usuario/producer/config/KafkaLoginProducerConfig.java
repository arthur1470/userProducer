package com.usuario.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.usuario.producer.controller.dto.LoginDTO;

@Configuration
public class KafkaLoginProducerConfig {

	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrapAddress;
	
	@Value(value = "${login.topic.name}")
	private String topic;
	
	public NewTopic createTopic() {
		return new NewTopic(topic, 3, (short) 1);
	}
	
	@Bean
    public ProducerFactory<String, LoginDTO> loginProducerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<String, LoginDTO>(config);
    }
    
	@Bean
    public KafkaTemplate<String, LoginDTO> kafkaLoginTemplate() {
        return new KafkaTemplate<String, LoginDTO>(loginProducerFactory());
    }
}
