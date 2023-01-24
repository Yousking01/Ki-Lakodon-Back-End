
package com.Kilakodon.kilakodon.repository;

import com.Kilakodon.kilakodon.models.Admin;
import com.Kilakodon.kilakodon.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);


    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    User findByEmail(String email);
}

