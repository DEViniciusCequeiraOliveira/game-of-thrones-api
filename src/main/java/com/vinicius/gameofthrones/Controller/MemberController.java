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


    @GetMapping("/house")
    public Page<MemberHouseDTO> getMemberHouse(Pageable pageable) {
        return memberRepository.findAll(pageable).map(MemberHouseDTO::new);
    }

    @GetMapping()
    public Page<MemberDTO> getMember(Pageable pageable) {
        return characterRepository.findAll(pageable).map(MemberDTO::new);
    }

    @GetMapping("/name/{name}")
    public MemberDTO getMemberByname(@PathVariable String name) {
        return characterRepository.findByNameIgnoreCase(name).map(MemberDTO::new).get();
    }

    @GetMapping("/house/{housesName}")
    public List<MemberDTO> getHouseByName(@PathVariable String housesName) {
        return characterRepository.findByHouseIgnoreCase(housesName)
                .stream()
                .map(MemberDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/name/id")
    public MemberDTO getMemberById(@RequestParam String id) {
        return characterRepository.findById(id).map(MemberDTO::new).get();
    }

    @GetMapping("/house/id")
    public MemberHouseDTO getMemberHouseByName(@RequestParam String id) {
        return memberRepository.findById(id).map(MemberHouseDTO::new).get();
    }
}
