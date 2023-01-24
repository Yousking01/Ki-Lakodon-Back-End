
package com.Kilakodon.kilakodon.security.services;

import com.Kilakodon.kilakodon.models.EspacePub;
import com.Kilakodon.kilakodon.models.Etat;
import com.Kilakodon.kilakodon.repository.EspacePubRepository;
import com.Kilakodon.kilakodon.repository.EtatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EspacepubServiceImpl implements EspacepubService{

    @Autowired
    private EspacePubRepository espacePubRepository;

    @Autowired
    private EspacepubService espacepubService;

    @Autowired
    private EtatRepository etatRepository;

    @Override
    public EspacePub creer(EspacePub espacePub) {
        return espacePubRepository.save(espacePub);
    }

    @Override
    public List<EspacePub> lister() {
        return espacePubRepository.findAll();
    }

    @Override
    public EspacePub findByIdespacepub(Long idespacepub) {
        return espacePubRepository.findById(idespacepub).orElse(null);
    }

    @Override
    public EspacePub getNomespacepub(String nomespacepub) {
        return espacePubRepository.findByNomespacepub(nomespacepub);
    }

    @Override
    public List<EspacePub> lire() {
        return espacePubRepository.findAll();
    }

    @Override
    public EspacePub modifier(Long idespacepub, EspacePub espacePub) {
        return espacePubRepository.findById(idespacepub)
                .map(p->{
                    p.setNomespacepub(espacePub.getNomespacepub());
                    p.setDescription(espacePub.getDescription());
                    p.setPrix(espacePub.getPrix());
                    //p.setEtat(espacePub.getEtat());
                    return espacePubRepository.save(espacePub);
                }).orElseThrow(()-> new RuntimeException("Espace non trouver"));
    }

    @Override
    public String suprimer(Long idespacepub) {
        return null;
    }

    @Override
    public String suprimer(long idespacepub) {
        espacePubRepository.deleteById(idespacepub);
        return "Espace Suprimer avec Succès";
    }

    @Override
    public Etat saveEtat(Etat etat) {
        return etatRepository.save(etat);
    }


/*@Autowired
    private EspacePubRepository espacePubRepository;


    @Override
    public EspacePub creer( EspacePub espacePub ) {
        return espacePubRepository.save(espacePub);
    }

    @Override
    public EspacePub creer(EspacepubController espacePub) {
        return null;
    }

    @Override
    public List<EspacePub> lister() {
        return espacePubRepository.findAll();
    }



    @Override
    public EspacePub findByIdespacepub(Long idespacepub) {
        return espacePubRepository.findById(idespacepub).orElse(null);
    }

    @Override
    public EspacePub modifier(Long idespacepub, EspacePub espacePub) {
        return espacePubRepository.findByIdespacepub(idespacepub)
                ;
    }

    @Override
    public String suprimer(Long idespacepub) {
        espacePubRepository.deleteById(idespacepub);
        return "Espace Suprimer Avec Succès";
    }
*/

}

