package com.adidas.subscription.subcription.service;

import com.adidas.subscription.subcription.dto.request.SubscriptionRequest;
import com.adidas.subscription.subcription.dto.response.SubscriptionResponse;
import java.util.List;
import java.util.UUID;

public interface Subscription {

  UUID createSubscription(SubscriptionRequest subscriptionInput);

  void cancelSubscription(UUID subscriptionId);

  SubscriptionResponse getDetailSubscription(UUID subscriptionId);

  List<SubscriptionResponse> getAllSubscription();

}
