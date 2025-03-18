package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Repository.CharacterRepository;
import com.vinicius.gameofthrones.Repository.MemberRepository;
import com.vinicius.gameofthrones.Service.MemberService;
import com.vinicius.gameofthrones.dto.MemberDTO;
import com.vinicius.gameofthrones.dto.MemberHouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    public ResponseEntity<Page<MemberHouseDTO>> getMemberHouse(Pageable pageable) {
        return ResponseEntity.ok(memberService.getMemberHouse(pageable));
    }

    @GetMapping("/house/id")
    public ResponseEntity<MemberHouseDTO> getMemberHouseByName(@RequestParam String id) {
        return ResponseEntity.ok(memberService.getMemberHouseByName(id));
    }
}
