package com.vinicius.gameofthrones.dto;

import com.vinicius.gameofthrones.Models.CastlesDados;
import com.vinicius.gameofthrones.Models.CastlesModel;

import java.awt.font.TextHitInfo;
import java.util.List;

public record CastlesDTO(
        String _id,
        String name,
        List<CastlesModel> castles
) {
    public CastlesDTO(CastlesDados dados) {
        this(dados.get_id(), dados.getName(), dados.getCastles());
    }
}
