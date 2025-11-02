package com.adidas.subscription.subcription.service.impl;

import com.adidas.subscription.subcription.dto.request.SubscriptionRequest;
import com.adidas.subscription.subcription.dto.response.SubscriptionResponse;
import com.adidas.subscription.subcription.entity.SubscriptionEntity;
import com.adidas.subscription.subcription.exception.DuplicatedException;
import com.adidas.subscription.subcription.exception.SqsExceptionCustom;
import com.adidas.subscription.subcription.kafka.KafkaProducerService;
import com.adidas.subscription.subcription.repository.SubscriptionRepository;
import com.adidas.subscription.subcription.service.Subscription;
import com.adidas.subscription.subcription.sqs.SqsClientService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionImpl implements Subscription {

  private final SubscriptionRepository subscriptionRepository;
  private final KafkaProducerService kafkaProducerService;
  private final SqsClientService sqsClientService;


  @Override
  @Transactional
  public SubscriptionResponse createSubscription(SubscriptionRequest subscriptionInput) {
    SubscriptionResponse subscriptionResponse = null;
    try {
      SubscriptionEntity entityCreated = subscriptionRepository.saveAndFlush(mapToEntity(subscriptionInput));
      subscriptionResponse = mapToResponse(entityCreated);
      kafkaProducerService.sendSubscriptionCreatedEvent(subscriptionResponse);
      sqsClientService.sendMessage(subscriptionResponse);
    }catch (DataIntegrityViolationException e) {
      log.error("Constraint violation (H2): {}", e.getMessage(), e);
      throw new DuplicatedException("This field is already in our records");
    }catch (Exception e){
      log.error("error no identificado {}", e.getMessage());
      throw new SqsExceptionCustom("Error sending the message");
    }
    return subscriptionResponse;
  }

  @Override
  public void cancelSubscription(UUID id) {
    SubscriptionEntity subscription = subscriptionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Subscription not found with ID: " + id));
    // Solo marcamos como cancelada, no la eliminamos físicamente (soft delete).
    subscription.setCancelSubscription(true);
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
        .cancelSubscription(false)
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
