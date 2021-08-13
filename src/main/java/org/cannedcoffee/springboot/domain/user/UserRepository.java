package org.cannedcoffee.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // method verifying if returned email is new
    Optional<User> findByEmail(String email);

}
