package com.example.demo.Infrastructure.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class StringProducerService {
    
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String message){
        kafkaTemplate.send("str-topic", message).whenComplete((result, ex)->{
            if(ex != null){
                log.error("error trying to send message {}", ex.getMessage());
            }

            log.info("Message sent succesfull: {}", result.getProducerRecord().value());
            log.info("partition {}, offset {}", result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
        });
    }
}
