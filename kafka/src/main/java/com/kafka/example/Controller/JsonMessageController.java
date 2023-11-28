package com.kafka.example.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.example.entity.User;
import com.kafka.example.kafkaproducer.JsonKafkaProducer;

@RestController
@RequestMapping("/api/jsonkafka")
public class JsonMessageController {

	private JsonKafkaProducer producer;

	public JsonMessageController(JsonKafkaProducer producer) {

		this.producer = producer;
	}

	@PostMapping("/publish")
	public ResponseEntity<String> publish(@RequestBody User u) {
		producer.sendMessage(u);
		return ResponseEntity.ok("json message sent to topic");
	}

}
