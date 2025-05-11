package org.example.test_task.service.userService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test_task.dto.user.UserDTO;
import org.example.test_task.entity.User;
import org.example.test_task.exception.user.UserCreationException;
import org.example.test_task.exception.user.UserException;
import org.example.test_task.exception.user.UserNotFoundException;
import org.example.test_task.exception.user.UserUpdateException;
import org.example.test_task.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        log.info("Creating new user with name: {}", userDTO.getName());
        try {
            User user = User.builder()
                    .name(userDTO.getName())
                    .surname(userDTO.getSurname())
                    .email(userDTO.getEmail())
                    .build();
            userRepository.save(user);
            log.info("User created successfully. ID: {}, Email: {}", user.getId(), user.getEmail());

            return modelMapper.map(user, UserDTO.class);
        } catch (UserCreationException e) {
            log.error("Error creating user with name: {}", userDTO.getName());
            throw new UserCreationException(e.getMessage());
        }
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        log.info("User with id was found:{},{},{}", userId, user.getName(), user.getSurname());
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteUserById(Long userId) {
        log.debug("Attempting to delete user with ID: {}", userId);
        try {
            if (!userRepository.existsById(userId)) {
                String errorMsg = String.format("User with ID: %s not found for deletion", userId);
                log.error(errorMsg);
                throw new UserNotFoundException(userId);
            }
            userRepository.deleteById(userId);
            log.info("User with ID:{} was deleted", userId);
        } catch (UserException e) {
            log.error("Failed to delete user", e);
            throw new UserException(e.getMessage(), e.getStatus());
        }
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        try {
            if (!userRepository.existsById(userId)) {
                log.error("User with ID:{} not found", userId);
                throw new UserNotFoundException(userId);
            }
            User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setEmail(userDTO.getEmail());
            User updatedUser = userRepository.save(user);

            return new UserDTO(updatedUser.getName(), updatedUser.getSurname(), updatedUser.getEmail());
        } catch (UserUpdateException e) {
            log.error("Failed to update user with ID:{}", userId);
            throw new UserUpdateException(e.getMessage());
        }
    }
}
