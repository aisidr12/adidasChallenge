package com.adidas.subscription.subcription.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
  @Value("${kafka.topic.subscription-created}")
  private String subscriptionCreatedTopic;

  /**
   * Define el tópico de suscripción.
   * Spring Boot usará KafkaAdmin (detectado por autoconfiguración) para crearlo o verificarlo.
   */
  @Bean
  public NewTopic subscriptionTopic() {
    // En un entorno de producción, los números de partición y réplicas
    // serían mayores y se obtendrían de la configuración.
    return TopicBuilder.name(subscriptionCreatedTopic)
        .partitions(3) // 3 particiones para permitir procesamiento paralelo
        .replicas(1)  // 1 réplica (idealmente 3 en prod para alta disponibilidad)
        .build();
  }
}
