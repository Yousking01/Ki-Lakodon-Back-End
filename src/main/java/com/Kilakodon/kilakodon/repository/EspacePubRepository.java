
package com.Kilakodon.kilakodon.repository;

import com.Kilakodon.kilakodon.models.EspacePub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspacePubRepository extends JpaRepository<EspacePub,Long> {

    EspacePub findByNomespacepub(String nomespacepub);


//EspacePub modifier(Long idespacepub, EspacePub espacePub);



//EspacePub modifier(Long idespacepub, EspacePub espacePub);


//Optional<EspacePub> findByIdannonceur(Long idannonceur);

}

