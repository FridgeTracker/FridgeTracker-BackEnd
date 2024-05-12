package com.example.FridgeTracker.Commands.MemberCommands;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.Member.Member;
import com.example.FridgeTracker.Member.MemberRepository;

import jakarta.transaction.Transactional;

public class DeleteMemberCommand implements Command{

    private MemberRepository memberRepository;
    private Member request;

    @Autowired
    public DeleteMemberCommand(Member request, MemberRepository memberRepository){
        this.request = request;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> execute() {
        Optional<Member> optionalMember = memberRepository.findById(request.getId());
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            memberRepository.delete(member);
            return ResponseEntity.ok("Successfully deleted Member");
        } else {
            return ResponseEntity.ok("Failed to delete Member");
        }
    }
    
}
