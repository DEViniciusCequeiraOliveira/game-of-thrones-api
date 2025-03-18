package com.vinicius.gameofthrones.Service;

import com.vinicius.gameofthrones.Repository.HouseRepository;
import com.vinicius.gameofthrones.dto.HouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {
    @Autowired
    HouseRepository houseRepository;

    public List<HouseDTO> getHouse(Pageable pageable) {
        return houseRepository.findAll(pageable).stream().map(HouseDTO::new).toList();
    }
    public HouseDTO getHouseById(String id) {
        return houseRepository.findById(id).map(HouseDTO::new).get();
    }
    public HouseDTO getHouseByName(String name) {
        return houseRepository.findByName(name).map(HouseDTO::new).get();
    }
}