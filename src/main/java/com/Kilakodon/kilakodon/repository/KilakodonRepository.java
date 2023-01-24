
package com.Kilakodon.kilakodon.repository;

import com.Kilakodon.kilakodon.models.Kilakodon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KilakodonRepository extends JpaRepository<Kilakodon, Long> {

    Kilakodon findByIdKilakodon(Long idKilakodon);
}

