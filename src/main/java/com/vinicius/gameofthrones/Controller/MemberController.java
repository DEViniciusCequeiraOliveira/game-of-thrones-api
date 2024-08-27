package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Repository.MemberRepository;
import com.vinicius.gameofthrones.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping
    public Page<MemberDTO> getMember(Pageable pageable) {
        return memberRepository.findAll(pageable).map(MemberDTO::new);
    }

    @GetMapping("/name{houses}")
    public MemberDTO getMemberHouse(@RequestParam String houses) {
        System.out.println(houses + " house");
        var member = memberRepository.findById(houses).map(MemberDTO::new);
        if (member.isPresent()) {
            return member.get();
        }
        return null;
    }

}
