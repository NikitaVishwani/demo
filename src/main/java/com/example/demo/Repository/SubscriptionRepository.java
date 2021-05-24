package com.example.demo.Repository;

import com.example.demo.Entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository
        extends JpaRepository<SubscriptionEntity, String> {
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Subscription sc WHERE sc.id = :tenantId AND sc.subscription_id = :serviceId ",nativeQuery = true)
    boolean checkSubscriptionID(@Param("tenantId") String tenantId, @Param("serviceId") String serviceId);

    @Query(value = "SELECT subscription_id FROM Subscription where sc.id = :tenantId ", nativeQuery = true)
    List<String> getServiceId(@Param("tenantId") String tenantId);
}
