package com.Kilakodon.kilakodon;

import com.Kilakodon.kilakodon.Image.JacksonConfig;
import com.Kilakodon.kilakodon.models.*;
import com.Kilakodon.kilakodon.repository.*;
import com.Kilakodon.kilakodon.security.services.EspacepubService;
import com.Kilakodon.kilakodon.security.services.EtatService;
import com.Kilakodon.kilakodon.security.services.NotificationService;
import com.Kilakodon.kilakodon.security.services.UserService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
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
//@EnableAutoConfiguration
@Import(JacksonConfig.class)
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
	private KilakodonRepository kilakodonRepository;



	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private NotificationService notificationService;


	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private EspacePubRepository espacePubRepository;


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
			Role r1 = userService.saveRole(new Role(ROLE_ADMIN));
			Role r2 = userService.saveRole(new Role(SITE_WEB));
			Role r3 = userService.saveRole(new Role(ANNONCEUR));
			Role r4 = userService.saveRole(new Role(ROLE_USER));
		}

		if (etatRepository.findAll().size() == 0) {
			Etat etat1 = espacepubService.saveEtat(new Etat(null, ESPACE_PUB_DISPONIBLE));
			Etat etat2 = espacepubService.saveEtat(new Etat(null, ESPACE_PUB_VENDU));
		}
		if (espacePubRepository.findAll().size() == 0) {
			Set<Etat> etats= new HashSet();
			Etat etat1= etatRepository.findByName(EspacePubEtat.ESPACE_PUB_DISPONIBLE);
			System.err.println(etat1.getName());
			etats.add(etat1);
			EspacePub espacekilakodon= new EspacePub();
			espacekilakodon.setNomespacepub("Kilakodon");
			espacekilakodon.setDescription("Espace pour passer des annonces de publicit??s.");
			espacekilakodon.setPrix(10000);
			espacekilakodon.setEtat(etat1);
			System.err.println(espacekilakodon.getDescription());
			espacePubRepository.save(espacekilakodon);

		}

		if (userRepository.findAll().size() == 0){
			Set<Role> roles= new HashSet();
			Role role1= roleRepository.findByName(ERole.ROLE_ADMIN);
			System.err.println(role1.getName());
			roles.add(role1);
			User admin= new User("youssouf djire","djireyoussouf1999@gmail.com", encoder.encode("12345678"),encoder.encode("12345678"));
			admin.setRoles(roles);
			userRepository.save(admin);
			User user = userRepository.save(admin);
				Notification notification = new Notification();
				notification.setDescriptionNotif("une nouvelle a ??t?? lanc?? !!!");
				notification.setUser(user);
				notification.setValidation(true);
				notification.setIdnotif(1L);

				notificationRepository.save(notification);
		}
		if(kilakodonRepository.findAll().size() == 0){
			Kilakodon kilakodon = new Kilakodon();
			kilakodon.setLibelleKilakodon("libelle1");
			System.err.println(kilakodon.getLibelleKilakodon());
			kilakodonRepository.save(kilakodon);
		}
	}

}
