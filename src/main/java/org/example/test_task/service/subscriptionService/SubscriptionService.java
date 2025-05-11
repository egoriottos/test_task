package org.example.test_task.service.subscriptionService;

import org.example.test_task.dto.subscription.CreateSubscriptionDTO;
import org.example.test_task.dto.subscription.SubscriptionDTO;
import org.example.test_task.dto.subscription.SubscriptionStatDTO;
import java.util.List;

public interface SubscriptionService {
    SubscriptionDTO addSubscription(Long userId, CreateSubscriptionDTO createSubscriptionDTO);

    List<SubscriptionDTO> getSubscriptionsByUserId(Long userId);

    void deleteSubscription(Long userId, Long subId);

    List<SubscriptionStatDTO> getTopThreePopularSubscriptions();
}
