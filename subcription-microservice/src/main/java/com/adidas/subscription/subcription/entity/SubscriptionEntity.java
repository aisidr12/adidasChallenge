package com.adidas.subscription.subcription.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long idSubscription;
  @Column(nullable = false, unique = true)
  private String email;
  @Column(nullable = false)
  private LocalDate birthday;
  @Column(nullable = false)
  private Boolean consent; // Remember when they are objects they serialize to null, not working for primitives
  @Column(nullable = false)
  private String newsLetterId;
  @Column
  private String firstName;
  @Column
  private String gender;
}
