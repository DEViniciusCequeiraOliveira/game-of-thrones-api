package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Models.HouseModel;
import com.vinicius.gameofthrones.Repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseRepository houseRepository;


    @GetMapping
    public List<HouseModel> getHouse(){
        return houseRepository.findAll();
    }
}
