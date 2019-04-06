package com.example.rabbit.service;

import com.example.rabbit.entity.Member;
import com.example.rabbit.sender.MemberRegisterSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRegisterSender memberRegisterSender;



    public Long memberRegister(Member member) throws Exception {
        //会员注册
        memberRegisterSender.sendMessage(member);
        return member.getId();
    }

}
