package com.lighter.granate.service;

import com.lighter.granate.dto.CreateUserDTO;
import com.lighter.granate.dto.UpdateUserDTO;
import com.lighter.granate.entity.User;
import com.lighter.granate.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public String createUser(CreateUserDTO dto) {
        User user = User.builder()
                .username(dto.getUsername().toLowerCase())
                .email(dto.getEmail().toLowerCase())
                .password(dto.getPassword())
                .build();
        return userRepository.save(user).toString();
    }

    public List<User> readUsers() {
        return userRepository.findAll();
    }

    public User readUser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

    public String updateUser(UpdateUserDTO dto) {
        if (!userRepository.existsById(dto.getId())) {
            return "User don`t exists.";
        }
        User user = User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        return userRepository.save(user).toString();
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
