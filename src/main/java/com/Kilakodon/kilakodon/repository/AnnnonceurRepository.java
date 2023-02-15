package com.Kilakodon.kilakodon.repository;

import com.Kilakodon.kilakodon.models.Annnonceur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AnnnonceurRepository extends JpaRepository<Annnonceur, Long> {
    Optional<Annnonceur> findByUsername(String username);

    Optional<Annnonceur> findById(Long id);
}
