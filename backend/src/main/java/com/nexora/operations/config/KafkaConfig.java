package com.nexora.operations.config;

import com.nexora.operations.event.DomainEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
  @Bean ConsumerFactory<String, DomainEvent> domainEventConsumerFactory(@Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
    Map<String, Object> props = new HashMap<>(); props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); props.put(ConsumerConfig.GROUP_ID_CONFIG, "entitlement-consumer"); props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class); props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, DomainEvent.class.getName()); props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.nexora.operations.event");
    return new DefaultKafkaConsumerFactory<>(props);
  }
  @Bean ConcurrentKafkaListenerContainerFactory<String, DomainEvent> kafkaListenerContainerFactory(ConsumerFactory<String, DomainEvent> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<String, DomainEvent> factory = new ConcurrentKafkaListenerContainerFactory<>(); factory.setConsumerFactory(consumerFactory); return factory;
  }
}
