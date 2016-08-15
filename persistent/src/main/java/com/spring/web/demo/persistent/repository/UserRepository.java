package com.spring.web.demo.persistent.repository;

import com.spring.web.demo.persistent.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends SimpleJpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE lower(u.email)=lower(:email)")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE lower(u.login)=lower(:login)")
    Optional<User> findByLogin(@Param("login") String login);
}
