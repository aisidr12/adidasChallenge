package com.adidas.subscription.subcription.service.impl;

import com.adidas.subscription.subcription.dto.request.SubscriptionRequest;
import com.adidas.subscription.subcription.dto.response.SubscriptionResponse;
import com.adidas.subscription.subcription.entity.SubscriptionEntity;
import com.adidas.subscription.subcription.kafka.KafkaProducerService;
import com.adidas.subscription.subcription.repository.SubscriptionRepository;
import com.adidas.subscription.subcription.service.Subscription;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionImpl implements Subscription {

  private final SubscriptionRepository subscriptionRepository;
  private final KafkaProducerService kafkaProducerService;


  @Override
  @Transactional
  public UUID createSubscription(SubscriptionRequest subscriptionInput) {
    SubscriptionEntity entity = mapToEntity(subscriptionInput);
    SubscriptionEntity entityCreated = subscriptionRepository.save(entity);
    kafkaProducerService.sendSubscriptionCreatedEvent(entityCreated);
    return entityCreated.getSubscriptionId();
  }

  @Override
  public void cancelSubscription(UUID id) {
//    SubscriptionEntity entity = mapToEntity(subscriptionInput);
//    subscriptionRepository.delete(entity);
    SubscriptionEntity subscription = subscriptionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Subscription not found with ID: " + id));
    // Solo marcamos como cancelada, no la eliminamos físicamente (soft delete).
    subscription.setCanceled(true);
    subscription.setUpdateAt(LocalDateTime.now());
    subscriptionRepository.save(subscription);
  }

  @Override
  public SubscriptionResponse getDetailSubscription(UUID id) {
    return subscriptionRepository
        .findById(id)
        .map(this::mapToResponse)
        .orElse(new SubscriptionResponse());
  }


  @Override
  public List<SubscriptionResponse> getAllSubscription() {
    return subscriptionRepository.findAll()
        .stream()
        .map(this::mapToResponse)
        .toList();
  }

  /**
   * Convert the input request into an entity, in order to be used by the repository
   *
   * @param subscriptionRequest the inputRequest with all the required information
   * @return New entity with the information filled
   */

  private SubscriptionEntity mapToEntity(SubscriptionRequest subscriptionRequest) {
    return SubscriptionEntity.builder()
        .firstName(subscriptionRequest.getFirstName())
        .birthday(subscriptionRequest.getDateOfBirth())
        .email(subscriptionRequest.getEmail())
        .newsLetterId(subscriptionRequest.getNewsletterId())
        .gender(subscriptionRequest.getGender())
        .consent(subscriptionRequest.getConsentFlag())
        .canceled(false)
        .build();
  }

  /**
   * Convert from entity into a responseDto
   *
   * @param entity Contains all the information required
   * @return the SubscriptionResponse with the information that we retrieve from entity
   */
  private SubscriptionResponse mapToResponse(SubscriptionEntity entity) {
    return new SubscriptionResponse(entity.getSubscriptionId(), entity.getEmail(),
        entity.getFirstName(), entity.getGender(), entity.getBirthday(), entity.getConsent(),
        entity.getNewsLetterId());
  }

}
