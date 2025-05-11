package org.example.test_task.repository;

import org.example.test_task.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query(value = "SELECT s.name, COUNT(s) as count FROM subscriptions s " +
                   "GROUP BY s.name " +
                   "ORDER BY count DESC LIMIT 3", nativeQuery = true)
    List<Object[]> findTopThreePopularSubscriptions();
}
