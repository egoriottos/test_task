package org.example.test_task.dto.subscription;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.test_task.entity.SubscriptionPeriod;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SubscriptionDTO {
    @NotBlank
    private String name;;
    @NotNull
    private Long userId;
    @NotNull
    private SubscriptionPeriod period;
}
