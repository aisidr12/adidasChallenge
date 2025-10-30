package com.adidas.challenge.email.email.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionEventDTO {
  private UUID subscriptionId;
  private String userEmail;
  private String userName;
  private String eventType; // CREATED, UPDATED, CANCELLED
  private String planName;

}
