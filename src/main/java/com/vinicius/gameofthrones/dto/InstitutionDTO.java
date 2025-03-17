package com.vinicius.gameofthrones.dto;

public record InstitutionDTO(
        String name,
        String type,
        String location,
        String rules,
        String religion,
        String placeNotes,
        String founded,
        String status
) {
}
