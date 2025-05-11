package org.example.test_task.service.subscriptionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test_task.dto.subscription.CreateSubscriptionDTO;
import org.example.test_task.dto.subscription.SubscriptionDTO;
import org.example.test_task.dto.subscription.SubscriptionStatDTO;
import org.example.test_task.entity.Subscription;
import org.example.test_task.entity.User;
import org.example.test_task.exception.subscribe.SubscribeCreateException;
import org.example.test_task.exception.subscribe.SubscribeException;
import org.example.test_task.exception.subscribe.SubscribeNotFoundException;
import org.example.test_task.exception.user.UserNotFoundException;
import org.example.test_task.repository.SubscriptionRepository;
import org.example.test_task.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public SubscriptionDTO addSubscription(Long userId, CreateSubscriptionDTO createSubscriptionDTO) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> {
                log.warn("User not found with id: {}", userId);
                return new UserNotFoundException(userId);
            });
            log.debug("User was found with id:{}", user.getId());
            Subscription subscription = Subscription.builder()
                    .name(createSubscriptionDTO.getName())
                    .user(user)
                    .startDate(LocalDate.now())
                    .period(createSubscriptionDTO.getPeriod())
                    .build();
            subscription.calculateEndDate();
            Subscription saved = subscriptionRepository.save(subscription);

            log.info("Created new subscription: ID={}, Name={}, UserID={}, Period={}, StartDate={}, EndDate={}",
                    saved.getId(),
                    saved.getName(),
                    userId,
                    saved.getPeriod(),
                    saved.getStartDate(),
                    saved.getEndDate());

            return modelMapper.map(saved, SubscriptionDTO.class);
        } catch (SubscribeCreateException ex) {
            throw new SubscribeCreateException(ex.getMessage());
        }
    }

    @Override
    public List<SubscriptionDTO> getSubscriptionsByUserId(Long userId) {
            User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
            log.info("User was found with id:{}", user.getId());

            List<Long> subscriptionsIds = user.getSubscriptions().stream().map(Subscription::getId).toList();
            List<Subscription> subscriptions = subscriptionRepository.findAllById(subscriptionsIds);
            log.info("Found {} subscriptions for user ID: {}", subscriptions.size(), userId);

            return subscriptions.stream()
                    .peek(sub -> log.debug("Subscription details - ID: {}, Name: {}, Period: {}",
                            sub.getId(), sub.getName(), sub.getPeriod()))
                    .map(c -> modelMapper.map(c, SubscriptionDTO.class))
                    .toList();
    }

    @Override
    @Transactional
    public void deleteSubscription(Long userId, Long subId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));

        Subscription subscription = user.getSubscriptions().stream()
                .filter(c -> c.getId().equals(subId))
                .findFirst().orElseThrow(()-> new SubscribeNotFoundException(subId));
        user.getSubscriptions().remove(subscription);
        subscriptionRepository.deleteById(subscription.getId());
    }

    @Override
    public List<SubscriptionStatDTO> getTopThreePopularSubscriptions() {
        try {
            List<Object[]> subscriptionsTop3 = subscriptionRepository.findTopThreePopularSubscriptions();
            log.info("Top 3 subscriptions:{}", subscriptionsTop3);

            return subscriptionsTop3.stream()
                    .map(result -> {
                        SubscriptionStatDTO dto = new SubscriptionStatDTO();
                        dto.setName((String) result[0]);
                        dto.setCount((Long) result[1]);
                        return dto;
                    })
                    .collect(Collectors.toList());
        } catch (SubscribeException ex) {
            throw new SubscribeException(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
