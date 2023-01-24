
package com.Kilakodon.kilakodon.security.services;


import com.Kilakodon.kilakodon.models.Etat;
import com.Kilakodon.kilakodon.repository.EtatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EtatServiceImpl implements EtatService {
    @Autowired
    private EtatRepository etatRepository;
    @Override
    public Etat save(Etat etat) {
        return etatRepository.save(etat);
    }
}

