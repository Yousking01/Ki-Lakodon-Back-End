
package com.Kilakodon.kilakodon.controllers;

import com.Kilakodon.kilakodon.models.User;
import com.Kilakodon.kilakodon.repository.UserRepository;
import com.Kilakodon.kilakodon.util.EmailConstructor;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {


    BCryptPasswordEncoder bCryptPasswordEncoder;
    JavaMailSender mailSender;
    UserRepository userRepository;

    EmailConstructor emailConstructor;

    @GetMapping("/resetpassword/{email}")
    public ResponseEntity<String> resetpassword(@PathVariable String email){
        User user = userRepository.findByEmail(email);
        if(!userRepository.existsByEmail(email)){
            return  new ResponseEntity<String>("email non trouvé",HttpStatus.BAD_REQUEST);
        }
        String password = RandomStringUtils.randomAlphanumeric(10);
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        mailSender.send(emailConstructor.constructResetPasswordEmail(user, password));
        return new ResponseEntity<>("Une email a été envoyé pour réinitialiser votre mots de passe",HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody HashMap<String, String> request) {
        String email = request.get("email");
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>("Utilisateur non trouvé", HttpStatus.BAD_REQUEST);
        }
        String currentPassword = request.get("currentpassword");
        String newPassword = request.get("newpassword");
        String confirmpassword = request.get("confirmpassword");
        if (!newPassword.equals(confirmpassword)) {
            return new ResponseEntity<>("Mots de passe ne correpondent pas", HttpStatus.BAD_REQUEST);
        }
        String userPassword = user.getPassword();
        try {
            if (newPassword != null && !newPassword.isEmpty() && !StringUtils.isEmpty(newPassword)) {
                if (bCryptPasswordEncoder.matches(currentPassword, userPassword)) {
                    String encryptedPassword=bCryptPasswordEncoder.encode(newPassword);
                    user.setPassword(encryptedPassword);
                    userRepository.save(user);
                    mailSender.send(emailConstructor.constructResetPasswordEmail(user, newPassword));                }
            } else {
                return new ResponseEntity<>("Mot de passe actuel incorrect", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Mots de passe modifier avec succès!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur est survenue: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}

