
package com.Kilakodon.kilakodon.controllers;


import com.Kilakodon.kilakodon.models.ERole;
import com.Kilakodon.kilakodon.models.Role;
import com.Kilakodon.kilakodon.models.User;
import com.Kilakodon.kilakodon.models.Utilisateur;
import com.Kilakodon.kilakodon.payload.request.SignupRequest;
import com.Kilakodon.kilakodon.payload.request.LoginRequest;
import com.Kilakodon.kilakodon.payload.response.JwtResponse;

import com.Kilakodon.kilakodon.payload.response.MessageResponse;
import com.Kilakodon.kilakodon.repository.RoleRepository;
import com.Kilakodon.kilakodon.repository.UserRepository;
import com.Kilakodon.kilakodon.repository.UtilisateurRepository;
import com.Kilakodon.kilakodon.security.jwt.JwtUtils;
import com.Kilakodon.kilakodon.security.services.UserDetailsImpl;


import com.Kilakodon.kilakodon.util.EmailConstructor;
import com.nimbusds.openid.connect.sdk.UserInfoResponse;
//import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger Log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    EmailConstructor emailConstructor;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;



    @GetMapping("/getAll")
    public List<Utilisateur> getAllUser(){
        return utilisateurRepository.findAll();
    }

    @GetMapping("/role")
    public List<Role> getAllRole(){ return roleRepository.findAll();}

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        //UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        //ICI NOUS CREEONS UNE INSTANCE DE CELUI QUI S'EST AUTHENTIFIER EN UTILSANT LA CLASS USERDETAILIMPL
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        //ON GENERE LE TOKEN EN LE STOKANT DIRECTEMENT DANS UN COOKIE
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        String jwt = jwtUtils.generateJwtToken(authentication);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        List<String> entite = new ArrayList<>();

        roles.forEach(role ->{
            entite.add(role);
        });

        Log.info("VOUS ETES AUTHENTIFIE AVEC SUCCESS");

        //METHODE PERMETTANT DE RETOURNER LES INFOS DE USER ET DE STOCKER LE JWT DANS LE COOKIES DE POSTMAN
        /*ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getPassword(),
                        roles));*/
        //On RETOURNE LE TOKEN ET LES INFOS DE UTILISATEURS
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
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {

        //VERICAFICATION DE L'EXISTANCE DE L'UTILISATUER
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Nom d'utilisateur déjà pris!"));
        }
        //VERIFICATION DE L'EXISTANCE DE L'EMAIL
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Cet email est déjà utilisé!"));
        }
        Log.info("Création d'un utilisateur");
        //CREATION d'un collaborateur


        // Create new user's account
        if (signUpRequest.getPassword().equals(signUpRequest.getConfirmpassword())){
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                encoder.encode(signUpRequest.getConfirmpassword()));

    //RECUPERATION DES ROLES DU COLLABORATEUR
        Set<String> strRoles = signUpRequest.getRole();
        //CREATION DE ROLE A L'INTERIEUR PERMMETANT DE STOCKER LES DIFFERENTS ENTRER PAR L'AD
        Set<Role> roles = new HashSet<>();
        System.err.println(strRoles);
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
                    case "ROLE_ADMIN":
                       // System.err.println("eeeeeeeeeeeeeeeee" );
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
                    case "ANNONCEUR":
                        Role annonceurRole = roleRepository.findByName(ERole.ANNONCEUR);
                        if (annonceurRole == null) {
                            ResponseEntity
                                    .badRequest()
                                    .body(new MessageResponse("Error: Role non fourni !"));
                        } else {
                            roles.add(annonceurRole);
                        }
                        //.orElseThrow(() -> new RuntimeException("Error: Role non fournit."));
                        break;
                    case "SITE_WEB":
                        Role siteWeb = roleRepository.findByName(ERole.SITE_WEB);
                        if (siteWeb == null) {
                            ResponseEntity
                                    .badRequest()
                                    .body(new MessageResponse("Error: Role non fourni !"));
                        } else {
                            roles.add(siteWeb);
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
            System.err.println(user);

            //utilisateurRepository.inserInToAdminRole(1L,1L);
        userRepository.save(user);
        mailSender.send(emailConstructor.constructNewUserEmail(user));

        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès!"));
    } else

    {
        return ResponseEntity.badRequest().body(new MessageResponse("Les mots de passe ne sont pas les mêmes"));
    }

    }

    //** MEHTODE PERMETTANT DE CE DECONNECTER **
    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {

        Log.info("COLLABORATEUR DECONNECTER AVEC SUCCESS");

        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("DECONNEXION REUSSI"));
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

