package com.adidas.subscription.subcription.repository;

import com.adidas.subscription.subcription.entity.SubscriptionEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {

  //Optional<SubscriptionEntity> findBySubscriptionId(String uuid);
  Optional<SubscriptionEntity>findByEmail(String email);

}