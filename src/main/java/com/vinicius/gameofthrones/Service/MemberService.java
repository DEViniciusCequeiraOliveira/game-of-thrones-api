package com.vinicius.gameofthrones.Service;

import com.vinicius.gameofthrones.Models.MembersModel;
import com.vinicius.gameofthrones.Repository.MemberRepository;
import com.vinicius.gameofthrones.dto.MemberHouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public Page<MemberHouseDTO> getMemberHouse(Pageable pageable) {
        return memberRepository.findAll(pageable).map(MemberHouseDTO::new);
    }
    public MembersModel saveMember(MembersModel member) {
        return memberRepository.save(member);
    }


    public MemberHouseDTO getMemberHouseByName(String id) {
        return memberRepository.findById(id).map(MemberHouseDTO::new).get();
    }


}
