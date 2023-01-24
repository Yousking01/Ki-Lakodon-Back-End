
package com.Kilakodon.kilakodon.repository;

import com.Kilakodon.kilakodon.models.EspacePubEtat;
import com.Kilakodon.kilakodon.models.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtatRepository extends JpaRepository<Etat, Long> {

    Optional<EspacePubEtat> findByName(EspacePubEtat name);

}

