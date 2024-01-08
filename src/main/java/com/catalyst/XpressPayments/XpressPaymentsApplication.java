package com.catalyst.XpressPayments;

import com.catalyst.XpressPayments.model.Role;
import com.catalyst.XpressPayments.model.User;
import com.catalyst.XpressPayments.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class XpressPaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(XpressPaymentsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder){
		return (args)->{
			User user = new User();
			user.setFirstName("Damilola");
			user.setLastName("Sanni");
			user.setEmail("test@gmail.com");
			user.setPhoneNumber("098765444");
			user.setPassword(passwordEncoder.encode("Olaitan123!!"));
			user.setRole(Role.USER);
			userRepository.save(user);
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*");
			}
		};
	}

}

