/*
package com.Kilakodon.kilakodon.security.services;

import com.Kilakodon.kilakodon.models.Admin;
import com.Kilakodon.kilakodon.models.User;
import com.Kilakodon.kilakodon.repository.AdminRepository;
import com.Kilakodon.kilakodon.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class AdminDetailServiceImpl implements UserDetailsService {
    @Autowired
    AdminRepository adminRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable avec ce nom d'utilisateur: " + username));
        return UserDetailsImpl.build(admin);
    }

}
*/
