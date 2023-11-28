package com.kafka.example.kafkaconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.example.entity.User;

@Service
public class JsonKafkaConsumer {

	private static final Logger LOGGER=LoggerFactory.getLogger(JsonKafkaConsumer.class);
	@KafkaListener(topics = "${spring.kafka.topic-json.name}",groupId = "myGroup")
	public void consumer(User u) {
		LOGGER.info(String.format("message received is ->%s", u.toString()));
	}

}
