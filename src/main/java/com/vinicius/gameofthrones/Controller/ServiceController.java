package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Service.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class ServiceController {
    @Autowired
    ServiceUtil service;

    @GetMapping(value = "/carrega-banco")
    public ResponseEntity<?> carregarBanco() throws IOException {
        service.saveCastles();
        service.saveCharacter();
        service.saveHose();
        service.saveMember();
        return ResponseEntity.ok("Salvo com sucesso");
    }

    @GetMapping(value = "/carrega-member")
    public ResponseEntity<?> carregarMember() throws IOException {
        service.saveMember();
        return ResponseEntity.ok("Salvo com sucesso");
    }

    @GetMapping(value = "/carrega-house")
    public ResponseEntity<?> carregarHouse() throws IOException {
        service.saveHose();
        return ResponseEntity.ok("Salvo com sucesso");
    }

    @GetMapping(value = "/carrega-charecter")
    public ResponseEntity<?> carregarCharecter() throws IOException {
        service.saveCharacter();
        return ResponseEntity.ok("Salvo com sucesso");
    }

    @GetMapping(value = "/carrega-castles")
    public ResponseEntity<?> carregarCastles() throws IOException {
        service.saveCastles();
        return ResponseEntity.ok("Salvo com sucesso");
    }
    @GetMapping(value = "/carrega-game")
    public ResponseEntity<?> carregarGot() throws IOException {
        service.saveGameOfThrones();
        return ResponseEntity.ok("Salvo com sucesso");
    }
}
