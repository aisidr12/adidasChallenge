package com.adidas.subscription.subcription.service;

import com.adidas.subscription.subcription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionImpl {

  private final SubscriptionRepository subscriptionRepository;


}
