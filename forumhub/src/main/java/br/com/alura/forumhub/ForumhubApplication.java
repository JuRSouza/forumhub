package br.com.alura.forumhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ForumhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumhubApplication.class, args);

		//Para eu conseguir ver a senha e colocar no banco de dados
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}


}
