package com.vinicius.gameofthrones.dto;

public record LoginResponse(String token, Long expiresIn) {
}
