package com.kafka.example.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.example.kafkaproducer.KafkaProducer;

@RestController
@RequestMapping("/api/kafka")
public class MessageController {
	
	
	private KafkaProducer producer;

	
	public MessageController(KafkaProducer producer) {
		
		this.producer = producer;
	}


	@GetMapping("/get")
	public ResponseEntity<String> publish(@RequestParam("message") String message) {
		producer.sendMessage(message);
		return ResponseEntity.ok("message sent to the topic");
	}

}
