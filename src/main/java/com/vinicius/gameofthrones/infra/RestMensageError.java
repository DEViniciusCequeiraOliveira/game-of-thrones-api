package com.vinicius.gameofthrones.infra;

import org.springframework.http.HttpStatus;

public record RestMensageError (
         HttpStatus status,
         String mensagem){}
