package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Repository.HouseRepository;
import com.vinicius.gameofthrones.dto.HouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseRepository houseRepository;


    @GetMapping
    public ResponseEntity<Page<HouseDTO>> getHouse(Pageable pageable){
        var c = houseRepository.findAll(pageable).map(HouseDTO::new);
        return ResponseEntity.ok().body(c);
    }
}
