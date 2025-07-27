package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Models.Season.SeasonModel;
import com.vinicius.gameofthrones.Service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/season")
class SeasonController {

    @Autowired
    SeasonService service;

    @GetMapping()
    public List<SeasonModel> getSeason() {
        return service.get();
    }

    @GetMapping("/delete")
    public String delete() {
        service.delete();
        return "Deletado com sucesso";
    }
}
