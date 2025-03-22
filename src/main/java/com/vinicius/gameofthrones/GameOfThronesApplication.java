package com.vinicius.gameofthrones;

import com.vinicius.gameofthrones.Service.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameOfThronesApplication implements CommandLineRunner {

	@Autowired
	ServiceUtil serviceUtil;
	public static void main(String[] args) {
		SpringApplication.run(GameOfThronesApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		serviceUtil.saveAll();
	}
}
