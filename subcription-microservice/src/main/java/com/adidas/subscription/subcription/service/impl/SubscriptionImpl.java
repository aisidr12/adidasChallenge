package com.adidas.subscription.subcription.service.impl;

import com.adidas.subscription.subcription.dto.request.SubscriptionRequest;
import com.adidas.subscription.subcription.dto.response.SubscriptionResponse;
import com.adidas.subscription.subcription.entity.SubscriptionEntity;
import com.adidas.subscription.subcription.repository.SubscriptionRepository;
import com.adidas.subscription.subcription.service.Subscription;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionImpl implements Subscription {

  private final SubscriptionRepository subscriptionRepository;


  @Override
  public UUID createSubscription(SubscriptionRequest subscriptionInput) {
    SubscriptionEntity entity = mapToEntity(subscriptionInput);
    SubscriptionEntity entityCreated = subscriptionRepository.save(entity);
    return entityCreated.getIdSubscription();
  }

  @Override
  public void cancelSubscription(SubscriptionRequest subscriptionInput) {
    SubscriptionEntity entity = mapToEntity(subscriptionInput);
    subscriptionRepository.delete(entity);
  }

  @Override
  public SubscriptionResponse getDetailSubscription(String subscriptionId) {
    return subscriptionRepository.findBySubscriptionId(subscriptionId).map(this::mapToResponse)
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
        .build();
  }

  /**
   * Convert from entity into a responseDto
   *
   * @param entity Contains all the information required
   * @return the SubscriptionResponse with the information that we retrieve from entity
   */
  private SubscriptionResponse mapToResponse(SubscriptionEntity entity) {
    return new SubscriptionResponse(entity.getIdSubscription(), entity.getEmail(),
        entity.getFirstName(), entity.getGender(), entity.getBirthday(), entity.getConsent(),
        entity.getNewsLetterId());
  }

}
