package com.adidas.challenge.email.email.consumer;

import com.adidas.challenge.email.email.dto.SubscriptionEventDTO;
import com.adidas.challenge.email.email.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionEventConsumer {

  private static final Logger logger = LoggerFactory.getLogger(SubscriptionEventConsumer.class);

  private final EmailService emailService;
  private final ObjectMapper objectMapper;
//  @Value("${kafka.topic.subscription-created}")
//  private String topicName;

  @KafkaListener(topics = "subscription-created-topic", groupId = "email-service-group")
  public void consumeSubscriptionEvent(String message) {
    try {
      logger.info("Mensaje recibido de Kafka: {}", message);

      // Deserializar el mensaje
      SubscriptionEventDTO event = objectMapper.readValue(message, SubscriptionEventDTO.class);

      // Procesar el evento y enviar email
      emailService.sendSubscriptionCreatedEmail(event);

      logger.info("Email enviado correctamente para subscription: {}", event.getSubscriptionId());

    } catch (Exception e) {
      logger.error("Error procesando mensaje de Kafka: {}", e.getMessage(), e);
    }
  }
}
