package com.example.orderservice.messagequeue.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@EnableKafka
@Configuration
public class KafkaProducerConfig {
    @Bean
    public ProducerFactory<String, String> consumerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(consumerFactory());
    }
}
