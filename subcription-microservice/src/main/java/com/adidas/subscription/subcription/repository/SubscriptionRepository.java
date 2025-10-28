package com.adidas.subscription.subcription.repository;

import com.adidas.subscription.subcription.entity.SubscriptionEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

  //Optional<SubscriptionEntity> findBySubscriptionId(String uuid);
  Optional<SubscriptionEntity>findByEmail(String email);

}