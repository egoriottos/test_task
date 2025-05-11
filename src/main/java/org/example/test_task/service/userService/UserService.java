package org.example.test_task.service.userService;

import org.example.test_task.dto.user.UserDTO;

/**
 * Сервис для управления пользователями
 *
 */
public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long userId);
    void deleteUserById(Long userId);
    UserDTO updateUser(Long userId, UserDTO userDTO);
}
