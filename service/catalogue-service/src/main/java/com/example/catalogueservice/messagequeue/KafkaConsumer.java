package com.example.catalogueservice.messagequeue;

import com.example.catalogueservice.entity.Catalogue;
import com.example.catalogueservice.mapper.MapMapper;
import com.example.catalogueservice.repository.CatalogueRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final CatalogueRepository catalogueRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "catalog-topic")
    public void updateQty(String kafkaMessage) {
        log.info("Kafka Message: ->" + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();

        try {
            map = objectMapper.readValue(kafkaMessage, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Catalogue entity = catalogueRepository.findByProductId((String) map.get("productId"));
        if (entity != null) {
            entity.setStock(entity.getStock() - (Integer)map.get("qty"));
            catalogueRepository.save(entity);
        }
    }
}
