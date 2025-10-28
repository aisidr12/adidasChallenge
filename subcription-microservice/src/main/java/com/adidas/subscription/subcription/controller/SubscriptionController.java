package com.adidas.subscription.subcription.controller;

import com.adidas.subscription.subcription.dto.request.SubscriptionRequest;
import com.adidas.subscription.subcription.dto.response.SubscriptionResponse;
import com.adidas.subscription.subcription.service.Subscription;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v0/Subscription")
@RequiredArgsConstructor
public class SubscriptionController {

  private final Subscription subscriptionService;

  @PostMapping("/create")
  public ResponseEntity<String> createSubscription(@RequestBody SubscriptionRequest request) {
    UUID subscription = subscriptionService.createSubscription(request);
    return ResponseEntity.ok().body(subscription.toString());
  }

  @PostMapping("/cancel/{subscriptionId}")
  public ResponseEntity<?> cancelSubscription(@PathVariable("subscriptionId") UUID subscriptionId) {
    subscriptionService.cancelSubscription(subscriptionId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/detail/{subscriptionId}")
  public ResponseEntity<SubscriptionResponse> getDetailSubscription(
      @PathVariable("subscriptionId") UUID subscriptionId) {
    SubscriptionResponse response = subscriptionService.getDetailSubscription(subscriptionId);
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("/list")
  public ResponseEntity<List<SubscriptionResponse>> getAllSubscriptions() {
    List<SubscriptionResponse> responses = subscriptionService.getAllSubscription();
    return ResponseEntity.ok().body(responses);
  }

}
