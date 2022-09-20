package com.govey.service.user.application;

import com.govey.controller.users.users.ChangePasswordRequest;
import com.govey.controller.users.users.UpdateUserRequest;
import com.govey.service.user.domain.Authority;
import com.govey.service.user.domain.User;
import com.govey.service.user.domain.UserType;
import com.govey.service.user.infrastructure.UserRepository;
import com.govey.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private EntityManager entityManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User add(UserDto request) {
        if (userRepository.findOneWithAuthoritiesByUsername(request.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .type(UserType.general)
                .name(request.getName())
                .nickname(request.getNickname())
                .email(request.getEmail())
                .level(0)
                .point(0)
                .gender(request.getGender())
                .birthday(request.getBirthday())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Page<User> list(int page, int limit) {
        return userRepository.findAll(
                PageRequest
                        .of(page, limit)
        );
    }

    @Transactional()
    public User update(UUID uuid, UpdateUserRequest request) throws Exception {
        User user = userRepository.findById(uuid).orElseThrow(() -> new IllegalStateException(
                uuid + " does not exists")
        );

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getBirthday() != null) {
            user.setBirthday(request.getBirthday());
        }
        if (request.getNickname() != null && !user.getNickname().equals(request.getNickname())) {
            if (userRepository.findByNickname(request.getNickname()).orElse(null) != null) {
                throw new RuntimeException("이미 사용중인 닉네임입니다.");
            }

            user.setNickname(request.getNickname());
        }

        return userRepository.save(user);
    }

    @Transactional()
    public User changePassword(UUID uuid, ChangePasswordRequest request) throws Exception {
        User user = userRepository.findById(uuid).orElseThrow(() -> new IllegalStateException(
                uuid + " does not exists")
        );
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    @Transactional()
    public User activate(UUID uuid) throws Exception {
        User user = userRepository.findById(uuid).orElseThrow(() -> new IllegalStateException(
                uuid + " does not exists")
        );
        user.setActivated(true);
        return userRepository.save(user);
    }

    @Transactional()
    public User deactivate(UUID uuid) throws Exception {
        User user = userRepository.findById(uuid).orElseThrow(() -> new IllegalStateException(
                uuid + " does not exists")
        );
        user.setActivated(false);
        return userRepository.save(user);
    }

    @Transactional()
    public User withdrawal(UUID uuid) throws Exception {
        User user = userRepository.findById(uuid).orElseThrow(() -> new IllegalStateException(
                uuid + " does not exists")
        );

        String postfix = "deleted";
        user.setEmail(user.getEmail()+postfix);
        user.setUsername(user.getUsername()+postfix);
        user.setNickname(user.getNickname()+postfix);
        user.setDeleted(true);
        user.setDeletedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> retrieve(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByUsername(String account) {
        return userRepository.findOneWithAuthoritiesByUsername(account);
    }

    @Transactional
    public Optional<User> getMyUserWithAuthorities() {
        User user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElseThrow(() -> new IllegalStateException(
                "does not exists")
        );
        if (!user.isActivated()) {
            throw new IllegalStateException("deactivated user");
        }

        userRepository.save(user);

        return Optional.of(user);
    }
}
