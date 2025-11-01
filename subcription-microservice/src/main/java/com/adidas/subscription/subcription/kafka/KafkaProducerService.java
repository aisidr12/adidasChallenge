package com.adidas.subscription.subcription.kafka;

import com.adidas.subscription.subcription.dto.response.SubscriptionResponse;
import com.adidas.subscription.subcription.entity.SubscriptionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

  private final KafkaTemplate<String, SubscriptionResponse> kafkaTemplate;
  // Se inyecta el nombre del tópico desde application.properties
  @Value("${kafka.topic.subscription-created}")
  private String topicName;

  /**
   * Envía la entidad de suscripción al tópico de Kafka.
   * Esta operación es no bloqueante (asíncrona).
   *
   * @param subscription La entidad de suscripción que contiene los datos a enviar.
   */
  public void sendSubscriptionCreatedEvent(SubscriptionResponse subscription) {
    // La clave del mensaje se establece como el ID único de la suscripción para
    // asegurar que todos los mensajes relacionados con un mismo ID vayan a la misma partición.
    // El valor es el objeto SubscriptionEntity (que se serializa a JSON).
    kafkaTemplate.send(topicName, subscription.id().toString(), subscription);
  }
}
