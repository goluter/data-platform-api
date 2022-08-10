package com.govey.domain.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    String UPDATE_USER_LAST_LOGIN = "UPDATE user SET last_login = :time WHERE account = :account";

    @Transactional
    @Modifying
    @Query(value = UPDATE_USER_LAST_LOGIN, nativeQuery = true)
    int updateUserLastLogin(@Param("account") String account, @Param("time")LocalDateTime time);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByAccount(String account);

    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByAccount(String account);
}
