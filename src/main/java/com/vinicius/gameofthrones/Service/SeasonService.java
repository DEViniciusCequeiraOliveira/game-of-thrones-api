package com.vinicius.gameofthrones.Service;

import com.vinicius.gameofthrones.Models.Season.SeasonModel;
import com.vinicius.gameofthrones.Repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonService {

    @Autowired
    SeasonRepository seasonRepository;

    public List<SeasonModel> get() {
        return seasonRepository.findAll();
    }

    public void delete() {
        seasonRepository.deleteAll();
    }
}
