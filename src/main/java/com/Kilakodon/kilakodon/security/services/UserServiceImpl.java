

package com.Kilakodon.kilakodon.security.services;

import com.Kilakodon.kilakodon.models.ERole;
import com.Kilakodon.kilakodon.models.Role;
import com.Kilakodon.kilakodon.models.User;
import com.Kilakodon.kilakodon.repository.RoleRepository;
import com.Kilakodon.kilakodon.repository.UserRepository;
import com.Kilakodon.kilakodon.util.EmailConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j

public class UserServiceImpl implements UserService{



    private PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private EmailConstructor emailConstructor;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String Supprimer(Long id_users) {
        return null;
    }

    @Override
    public String Modifier(User users) {
        return null;
    }

    @Override
    public List<User> Afficher() {
        return null;
    }

    @Override
    public User Ajouter(User user) {
        log.info("Enregistre un nouveau User {} dans la base", user.getUsername());
        user.getEmail();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmpassword(passwordEncoder.encode(user.getConfirmpassword()));


        userRepository.save(user);
        mailSender.send(emailConstructor.constructNewUserEmail(user));
        return user;
    }

    @Override
    public User saveUser(User user) {

        return null;
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Enregistrement d'un nouveau role {} ndans la base de données", role.getName());

        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, ERole roleName) {
        log.info("Ajout d'un role {} à l'utilisateur ", email, roleName);
        User user= userRepository.findByEmail(email);
        Role role= roleRepository.findByName(roleName);


    }

    @Override
    public void resetPassword(User user) {

    }

    @Override
    public User findByEmail(String userEmail) {
        return null;
    }

    @Override
    public void updateUserPassword(User user, String newpassword) {

    }
}


