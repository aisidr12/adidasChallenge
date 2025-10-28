package com.adidas.subscription.subcription.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record SubscriptionResponse(UUID id,
                                   String email,
                                   String firstName,
                                   String gender,
                                   LocalDate birthday,
                                   Boolean consentFlag,
                                   String newsLetterId
) {

  public SubscriptionResponse() {
    this(null, null, null, null, null, null, null);
  }


}
