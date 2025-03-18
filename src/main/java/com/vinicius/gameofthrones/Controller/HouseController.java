package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Service.HouseService;
import com.vinicius.gameofthrones.dto.HouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseService houseService;


    @GetMapping
    public ResponseEntity<List<HouseDTO>> getHouse(Pageable pageable) {
        var response = houseService.getHouse(pageable);
        return ResponseEntity.ok().body(response);

    }
    public ResponseEntity<?> getHouseById(String id){
        var respose = houseService.getHouseById(id);
        return ResponseEntity.ok().body(respose);
    }
    public ResponseEntity<?> getHouseByName(String name){
        var respose = houseService.getHouseByName(name);
        return ResponseEntity.ok().body(respose);
    }
}