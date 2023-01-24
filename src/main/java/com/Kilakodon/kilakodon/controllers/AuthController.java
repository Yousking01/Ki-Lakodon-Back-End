
package com.Kilakodon.kilakodon.controllers;


import com.Kilakodon.kilakodon.models.ERole;
import com.Kilakodon.kilakodon.models.Role;
import com.Kilakodon.kilakodon.models.User;
import com.Kilakodon.kilakodon.payload.request.SignupRequest;
import com.Kilakodon.kilakodon.payload.request.LoginRequest;
import com.Kilakodon.kilakodon.payload.response.JwtResponse;

import com.Kilakodon.kilakodon.payload.response.MessageResponse;
import com.Kilakodon.kilakodon.repository.RoleRepository;
import com.Kilakodon.kilakodon.repository.UserRepository;
import com.Kilakodon.kilakodon.security.jwt.JwtUtils;
import com.Kilakodon.kilakodon.security.services.UserDetailsImpl;


import com.Kilakodon.kilakodon.util.EmailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    EmailConstructor emailConstructor;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signinAdmin")
    public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl adminDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = adminDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                adminDetails.getId(),
                adminDetails.getUsername(),
                adminDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        if (signUpRequest.getPassword().equals(signUpRequest.getConfirmpassword())){
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                encoder.encode(signUpRequest.getConfirmpassword()));


        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER);
            if (userRole == null) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Role non fourni !"));
            } else {
                roles.add(userRole);
            }
            //.orElseThrow(() -> new RuntimeException("Error: Role non fournit."));

        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
                        if (adminRole == null) {
                            ResponseEntity
                                    .badRequest()
                                    .body(new MessageResponse("Error: Role non fourni !"));
                        } else {
                            roles.add(adminRole);
                        }
                        //.orElseThrow(() -> new RuntimeException("Error: Role non fournit."));
                        break;

                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER);
                        if (userRole == null) {
                            ResponseEntity
                                    .badRequest()
                                    .body(new MessageResponse("Error: Role non fourni !"));
                        } else {
                            roles.add(userRole);
                        }
                        //.orElseThrow(() -> new RuntimeException("Error: Role non fournit."));
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        mailSender.send(emailConstructor.constructNewUserEmail(user));

        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès!"));
    } else

    {
        return ResponseEntity.badRequest().body(new MessageResponse("Les mots de passe ne sont pas les mêmes"));
    }

    }
}


/*if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });*//*




/*user.setRoles(roles);
        userRepository.save(user);
        mailSender.send(emailConstructor.constructNewUserEmail(user));

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));*/
//    }

//}

