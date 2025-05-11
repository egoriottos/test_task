package org.example.test_task.dto.subscription;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.test_task.entity.SubscriptionPeriod;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSubscriptionDTO{
        @NotBlank(message = "Subscription name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        private String name;
        @NotNull(message = "Period is required")
        private SubscriptionPeriod period;
}
