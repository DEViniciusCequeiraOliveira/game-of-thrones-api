package com.vinicius.gameofthrones;

import com.vinicius.gameofthrones.Service.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class GameOfThronesApplication implements CommandLineRunner {

	@Autowired
	ServiceUtil serviceUtil;
	public static void main(String[] args) {
		SpringApplication.run(GameOfThronesApplication.class, args);
	}

<<<<<<< Updated upstream
	@Autowired
	ServiceUtil serviceUtil;

=======
>>>>>>> Stashed changes
	@Override
	public void run(String... args) throws Exception {
		serviceUtil.saveAll();
	}
}
