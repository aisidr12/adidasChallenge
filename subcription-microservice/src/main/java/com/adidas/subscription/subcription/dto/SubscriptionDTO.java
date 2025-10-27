package com.adidas.subscription.subcription.dto;

import java.time.LocalDate;

public record SubscriptionDTO(String email,
                              String firstName,
                              String gender,
                              LocalDate birthday,
                              Boolean acceptNewsletter
) {

}
