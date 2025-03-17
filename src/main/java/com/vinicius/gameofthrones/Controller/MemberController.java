package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Models.CharacterModel;
import com.vinicius.gameofthrones.Repository.CharacterRepository;
import com.vinicius.gameofthrones.Repository.MemberRepository;
import com.vinicius.gameofthrones.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CharacterRepository characterRepository;


    @GetMapping("/member/house")
    public Page<MemberHouseDTO> getMemberHouse(Pageable pageable) {
        return memberRepository.findAll(pageable).map(MemberHouseDTO::new);
    }

    @GetMapping("/member")
    public Page<MemberDTO> getMember(Pageable pageable) {
        return characterRepository.findAll(pageable).map(MemberDTO::new);
    }

    @GetMapping("/id{name}")
    public MemberDTO getMemberById(@RequestParam String name) {
        return characterRepository.findByNameIgnoreCase(name).map(MemberDTO::new).get();
    }

    @GetMapping("/name{houses}")
    public List<MemberDTO> getMemberHouseByName(@RequestParam String housesName) {
        return characterRepository.findByHouseIgnoreCase(housesName)
                .stream()
                .map(MemberDTO::new)
                .collect(Collectors.toList());
    }
}
