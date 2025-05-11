package org.example.test_task.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.test_task.dto.subscription.CreateSubscriptionDTO;
import org.example.test_task.dto.subscription.SubscriptionDTO;
import org.example.test_task.dto.subscription.SubscriptionStatDTO;
import org.example.test_task.dto.user.UserDTO;
import org.example.test_task.service.subscriptionService.SubscriptionService;
import org.example.test_task.service.userService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class Controller {
    private final SubscriptionService subscriptionService;
    private final UserService userService;

    private static final String USERS_PATH = "/users";
    private static final String SUBSCRIPTION_PATH = "/subscriptions";

    @PostMapping(USERS_PATH)
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @GetMapping(USERS_PATH + "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping(USERS_PATH + "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping(USERS_PATH + "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User was deleted with ID: " + id);
    }

    @PostMapping(USERS_PATH + "/{id}" + SUBSCRIPTION_PATH)
    public ResponseEntity<SubscriptionDTO> createSub(@PathVariable("id") Long userId, @RequestBody @Valid CreateSubscriptionDTO dto) {
        return ResponseEntity.ok(subscriptionService.addSubscription(userId, dto));
    }

    @GetMapping(USERS_PATH + "/{id}" + SUBSCRIPTION_PATH)
    public ResponseEntity<List<SubscriptionDTO>> getSubscriptionsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByUserId(id));
    }

    @DeleteMapping(USERS_PATH + "/{id}" + SUBSCRIPTION_PATH+"/{subId}")
    public ResponseEntity<String> deleteSub(@PathVariable("id")Long id, @PathVariable("subId")Long subId) {
        subscriptionService.deleteSubscription(id,subId);
        return ResponseEntity.ok("Sub with ID: "+subId+" was deleted at user with id: "+subId);
    }

    @GetMapping(SUBSCRIPTION_PATH+"/top")
    public ResponseEntity<List<SubscriptionStatDTO>> getTopThreeSub() {
        return ResponseEntity.ok(subscriptionService.getTopThreePopularSubscriptions());
    }
}
