package com.vinicius.gameofthrones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vinicius.gameofthrones.Util.ScrapingUtil;

@SpringBootApplication
public class GameOfThronesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameOfThronesApplication.class, args);
		try {
			ScrapingUtil.getDados();	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
