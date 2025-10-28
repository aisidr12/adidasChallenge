package com.adidas.subscription.subcription.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID subscriptionId;
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
  @Column
  private Boolean canceled;
  @Column
  private LocalDateTime updateAt;
}
