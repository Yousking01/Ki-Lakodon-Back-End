
package com.Kilakodon.kilakodon.repository;

import com.Kilakodon.kilakodon.models.Annonceur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnnonceurRepository extends JpaRepository<Annonceur, Long>{

    Optional<Annonceur> findByUsername(String username);

    Optional<Annonceur> findById(Long id);


/*@Override*//*
*/
/*
    Optional<Annonceur> findByIdannonceur(Long idannonceur);*/

}

