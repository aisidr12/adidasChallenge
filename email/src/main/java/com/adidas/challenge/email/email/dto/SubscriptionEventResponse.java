package com.adidas.challenge.email.email.dto;

import java.time.LocalDate;
import java.util.UUID;

public record SubscriptionEventResponse(UUID id,
                                       String email,
                                       String firstName,
                                       String gender,
                                       LocalDate birthday,
                                       Boolean acceptNews,
                                       String newsLetterId) {

}
