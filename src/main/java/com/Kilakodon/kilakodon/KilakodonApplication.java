package com.Kilakodon.kilakodon;

import com.Kilakodon.kilakodon.models.*;
import com.Kilakodon.kilakodon.repository.AdminRepository;
import com.Kilakodon.kilakodon.repository.EtatRepository;
import com.Kilakodon.kilakodon.repository.RoleRepository;
import com.Kilakodon.kilakodon.repository.UserRepository;
import com.Kilakodon.kilakodon.security.services.EspacepubService;
import com.Kilakodon.kilakodon.security.services.EtatService;
import com.Kilakodon.kilakodon.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.beans.Encoder;
import java.util.HashSet;
import java.util.Set;

import static com.Kilakodon.kilakodon.models.ERole.*;
import static com.Kilakodon.kilakodon.models.EspacePubEtat.ESPACE_PUB_DISPONIBLE;
import static com.Kilakodon.kilakodon.models.EspacePubEtat.ESPACE_PUB_VENDU;


@SpringBootApplication
@EnableAutoConfiguration
public class KilakodonApplication implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserService userService;

	@Autowired
	private EtatRepository etatRepository;

	@Autowired
	private EtatService etatService;
	@Autowired
	private EspacepubService espacepubService;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PasswordEncoder encoder;


	public static void main(String[] args) {
		SpringApplication.run(KilakodonApplication.class, args);
	}


	/*@Primary
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}*/

	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.findAll().size() == 0) {
			Role r1 = userService.saveRole(new Role(null, ROLE_ADMIN));
			Role r2 = userService.saveRole(new Role(null, ROLE_USER));
			Role r3 = userService.saveRole(new Role(null, ROLE_MODERATOR));
		}

		if (etatRepository.findAll().size() == 0) {
			Etat etat1 = espacepubService.saveEtat(new Etat(null, ESPACE_PUB_DISPONIBLE));
			Etat etat2 = espacepubService.saveEtat(new Etat(null, ESPACE_PUB_VENDU));
		}

		if (adminRepository.findAll().size() == 0){
			Set<Role> role= new HashSet();
			Role role1= roleRepository.findByName(ERole.ROLE_ADMIN);
			System.err.println(role1.getName());
			role.add(role1);
			User admin= new User("youssouf djire","djireyoussouf1999@gmail.com", encoder.encode("12345678"),encoder.encode("12345678"));
			admin.setRoles(role);
			userRepository.save(admin);
			System.err.println(admin.getRoles().size());

		}

	}

}
