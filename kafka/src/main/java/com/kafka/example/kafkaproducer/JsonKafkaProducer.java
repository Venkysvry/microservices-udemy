package com.kafka.example.kafkaproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kafka.example.entity.User;

@Service
public class JsonKafkaProducer {
	
	@Value("${spring.kafka.topic-json.name}")
	private String jsontopicName;
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);
	private KafkaTemplate<String, User> kafkatemplate;

	public JsonKafkaProducer(KafkaTemplate<String, User> kafkatemplate) {
		super();
		this.kafkatemplate = kafkatemplate;
	}

	public void sendMessage(User u) {
		System.out.println("send message");
		LOGGER.info(String.format("message sent is ->%s", u.toString()));
		Message<User> message = MessageBuilder.withPayload(u).setHeader(KafkaHeaders.TOPIC, jsontopicName).build();
		kafkatemplate.send(message);
	}
}
