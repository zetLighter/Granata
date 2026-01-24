package com.lighter.granate.service;

import com.lighter.granate.dto.RegistrationUserDto;
import com.lighter.granate.entity.User;
import com.lighter.granate.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь %s не найден.", username)));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getTitle())).collect(Collectors.toList())
        );
    }

    public User createUser(RegistrationUserDto dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .roles(Set.of(roleService.getUserRole()))
                .build();
        return userRepository.save(user);
    }

    public User getUserFromToken() {
        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authToken.getPrincipal();
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User from token not found."));
    }

    public Optional<User> readByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
