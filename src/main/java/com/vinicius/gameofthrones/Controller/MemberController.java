package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Models.MembersModel;
import com.vinicius.gameofthrones.Service.MemberService;
import com.vinicius.gameofthrones.dto.MemberHouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;


    @PostMapping("/save")
    public ResponseEntity<MembersModel> saveMember(@RequestBody MembersModel member) {
        return ResponseEntity.ok(memberService.saveMember(member));
    }

    @GetMapping()
    public ResponseEntity<Page<MemberHouseDTO>> getMemberHouse(Pageable pageable) {
        return ResponseEntity.ok(memberService.getMemberHouse(pageable));
    }

    @GetMapping("/house/id")
    public ResponseEntity<MemberHouseDTO> getMemberHouseByName(@RequestParam String id) {
        return ResponseEntity.ok(memberService.getMemberHouseByName(id));
    }
}
