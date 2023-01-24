
package com.Kilakodon.kilakodon.security.services;

import com.Kilakodon.kilakodon.models.Kilakodon;
import com.Kilakodon.kilakodon.repository.KilakodonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KilakodonServiceImpl implements KilakodonService{

    @Autowired
    private KilakodonRepository kilakodonRepository;

    @Override
    public Kilakodon save(Kilakodon kilakodon) {
        return kilakodonRepository.save(kilakodon);
    }

    @Override
    public Optional<Kilakodon> getByIdkilakodon(Long idKilakodon) {
        return kilakodonRepository.findById(idKilakodon);
    }

    @Override
    public List<Kilakodon> lire() {
        return kilakodonRepository.findAll();
    }

    @Override
    public String suprimer(Long idKilakodon) {
        kilakodonRepository.deleteById(idKilakodon);
        return "Kilakodon Supprimer avec Succès!!";
    }

    @Override
    public Kilakodon modifier(Long idKilakodon, Kilakodon Kilakodon) {
        return kilakodonRepository.findById(idKilakodon)
                .map(p ->{
                    p.setLibelleKilakodon(Kilakodon.getLibelleKilakodon());

                    return kilakodonRepository.save(Kilakodon);
                }).orElseThrow(() -> new RuntimeException( "Kilakodon non disponible !!"));
    }
}

