package org.example.test_task.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    private String surname;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
}
