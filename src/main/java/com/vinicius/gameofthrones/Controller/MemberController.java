package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Service.MemberService;
import com.vinicius.gameofthrones.dto.MemberHouseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/member")
@Tag(name = "Member", description = "Pega os membros de cada casa")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    @Operation(summary = "Lista todos os membros de cada casa", description = "Retorna uma lista de membros de todas as casas paginada.")
    public ResponseEntity<Page<MemberHouseDTO>> getMemberHouse(Pageable pageable) {
        return ResponseEntity.ok(memberService.getMemberHouse(pageable));
    }

    @GetMapping("/house/{id}")
    @Operation(summary = "Busca os membros de uma casa por ID", description = "Retorna os detalhes de todos os membro de uma casa pelo id")
    public ResponseEntity<MemberHouseDTO> getMemberHouseById(@PathVariable String id) {
        return ResponseEntity.ok(memberService.getHouseById(id));
    }
}
