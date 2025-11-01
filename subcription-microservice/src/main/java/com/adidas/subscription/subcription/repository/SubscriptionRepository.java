package com.adidas.subscription.subcription.repository;

import com.adidas.subscription.subcription.entity.SubscriptionEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {

}