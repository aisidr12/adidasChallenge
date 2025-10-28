package com.adidas.subscription.subcription.service;

import com.adidas.subscription.subcription.dto.request.SubscriptionRequest;
import com.adidas.subscription.subcription.dto.response.SubscriptionResponse;
import java.util.List;
import java.util.UUID;

public interface Subscription {

  UUID createSubscription(SubscriptionRequest subscriptionInput);

  void cancelSubscription(SubscriptionRequest subscriptionInput);

  SubscriptionResponse getDetailSubscription(String subscriptionId);

  List<SubscriptionResponse> getAllSubscription();

}
