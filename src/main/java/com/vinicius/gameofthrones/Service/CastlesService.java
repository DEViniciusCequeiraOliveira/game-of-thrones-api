package com.vinicius.gameofthrones.Service;

import com.vinicius.gameofthrones.Repository.CastlesRepository;
import com.vinicius.gameofthrones.dto.CastlesDTO;
import com.vinicius.gameofthrones.dto.InstitutionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CastlesService {
    @Autowired
    private CastlesRepository castlesRepository;

    public List<CastlesDTO> getCastlesRegion() {
        return castlesRepository.findAll().stream().map(CastlesDTO::new).toList();
    }

    public List<InstitutionDTO> getCastles() {
        return castlesRepository.findAll().stream()
                .flatMap(dados -> dados.getCastles().stream())
                .filter(dados -> !dados.getName().contains("Category"))
                .map(castles -> new InstitutionDTO(
                        castles.getName(),
                        castles.getType(),
                        castles.getLocation(),
                        castles.getRules(),
                        castles.getReligion(),
                        castles.getPlaceNotes(),
                        castles.getFounded(),
                        castles.getStatus())
                )
                .collect(Collectors.toList());
    }
}
