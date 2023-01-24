
package com.Kilakodon.kilakodon.security.services;

import com.Kilakodon.kilakodon.models.Kilakodon;

import java.util.List;
import java.util.Optional;

public interface KilakodonService {
        Kilakodon save(Kilakodon kilakodon);

        Optional<Kilakodon> getByIdkilakodon(Long idKilakodon);

        List<Kilakodon> lire();

        String suprimer(Long idKilakodon);

        Kilakodon modifier(Long idKilakodon, Kilakodon Kilakodon);

}

