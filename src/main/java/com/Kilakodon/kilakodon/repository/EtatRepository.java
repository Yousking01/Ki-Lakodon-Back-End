
package com.Kilakodon.kilakodon.repository;

import com.Kilakodon.kilakodon.models.ERole;
import com.Kilakodon.kilakodon.models.EspacePubEtat;
import com.Kilakodon.kilakodon.models.Etat;
import com.Kilakodon.kilakodon.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtatRepository extends JpaRepository<Etat, Long> {

    //Optional<EspacePubEtat> findByName(EspacePubEtat name);
    //Role findByName(ERole name);
    Etat findByName(EspacePubEtat name);
}

