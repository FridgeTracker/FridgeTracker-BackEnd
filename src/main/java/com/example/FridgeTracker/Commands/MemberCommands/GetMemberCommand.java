package com.example.FridgeTracker.Commands.MemberCommands;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.Member.Member;
import com.example.FridgeTracker.Member.MemberRepository;

public class GetMemberCommand implements Command{

    private UUID request;
    private MemberRepository memberRepository;

    @Autowired
    public GetMemberCommand(UUID request, MemberRepository memberRepository){
        this.request = request;
        this.memberRepository = memberRepository;
    }

    @Override
    public ResponseEntity<?> execute() {
        Optional<Member> member = memberRepository.findById(request);
       
       if(member != null){
           return ResponseEntity.ok(member.get()); 
       } else {
           return ResponseEntity.status(600).body(null); 
       }
    }
    
}
