package com.estsoft.springproject.user.repository;

import com.estsoft.springproject.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    // select * from users where email = ${email} ;
    Optional<Users> findByEmail(String email);
}
