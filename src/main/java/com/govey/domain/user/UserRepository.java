package com.govey.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    String UPDATE_USER_LAST_LOGIN = "UPDATE user SET last_login = :time WHERE account = :account";

    @Transactional
    @Modifying
    @Query(value = UPDATE_USER_LAST_LOGIN, nativeQuery = true)
    int updateUserLastLogin(@Param("account") String account, @Param("time")LocalDateTime time);

    User findByAccount(String account);
}
