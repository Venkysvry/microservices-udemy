package com.kafka.example.kafkaproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
	@Value("${spring.kafka.topic.name}")
	private String topicName;
	public static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
	@Autowired
	private KafkaTemplate<String, String> kafkatemplate;

	public KafkaProducer(KafkaTemplate<String, String> kafkatemplate) {

		this.kafkatemplate = kafkatemplate;
	}

	public void sendMessage(String message) {
		LOGGER.info(String.format("Message sent %s ", message));
		kafkatemplate.send(topicName, message);

	}
}
