package com.adidas.subscription.subcription.service.impl;

import com.adidas.subscription.subcription.dto.response.SubscriptionResponse;
import com.adidas.subscription.subcription.dto.request.SubscriptionRequest;
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

  }

  @Override
  public SubscriptionResponse getDetailSubscription(SubscriptionRequest subscriptionInput) {
    return null;
  }

  @Override
  public List<SubscriptionResponse> getAllSubscription() {
    return List.of();
  }

  private SubscriptionEntity mapToEntity(SubscriptionRequest subscriptionRequest){
    return SubscriptionEntity.builder()
        .firstName(subscriptionRequest.getFirstName())
        .birthday(subscriptionRequest.getDateOfBirth())
        .email(subscriptionRequest.getEmail())
        .newsLetterId(subscriptionRequest.getNewsletterId())
        .gender(subscriptionRequest.getGender())
        .consent(subscriptionRequest.getConsentFlag())
        .build();
  }

}
