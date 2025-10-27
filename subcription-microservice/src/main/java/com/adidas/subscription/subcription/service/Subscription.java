package com.adidas.subscription.subcription.service;

import com.adidas.subscription.subcription.dto.response.SubscriptionResponse;
import com.adidas.subscription.subcription.dto.request.SubscriptionRequest;
import java.util.List;
import java.util.UUID;

public interface Subscription {

  UUID createSubscription(SubscriptionRequest subscriptionInput);

  void cancelSubscription(SubscriptionRequest subscriptionInput);

  SubscriptionResponse getDetailSubscription(SubscriptionRequest subscriptionInput);

  List<SubscriptionResponse> getAllSubscription();

}
