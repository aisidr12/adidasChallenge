package com.adidas.subscription.subcription.entity;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Subscription {

  private String email;
  private String firstName;
  private String gender;
  private LocalDate birthday;
  private boolean acceptNewsletter;

}
