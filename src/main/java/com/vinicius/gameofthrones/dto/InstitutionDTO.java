package com.vinicius.gameofthrones.dto;

import com.vinicius.gameofthrones.Models.CastlesModel;

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
    public InstitutionDTO(CastlesModel castlesModel) {
        this(
                castlesModel.getName(),
                castlesModel.getType(),
                castlesModel.getLocation(),
                castlesModel.getRules(),
                castlesModel.getReligion(),
                castlesModel.getPlaceNotes(),
                castlesModel.getFounded(),
                castlesModel.getStatus()
        );
    }
}
