package com.example.FridgeTracker.Member;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // Temporary add member endpoint without error control
    @PostMapping("/addMember")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addMemberToFamily(@RequestBody MemberRequest request){
        return memberService.addMemberToFamily(request);
    }

    //GET MEMBER
    @GetMapping("/member/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Member> getMember(@PathVariable UUID id){
        return memberService.getMember(id);
    }

    //UPDATE MEMBER IN FAMILY
    @PostMapping("/updateMember")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> updateMemberInFamily(@RequestBody MemberRequest request){
        return memberService.updateMemberInFamily(request);
    }


    //DELETE MEMBER FROM FAMILY
    @PostMapping("/deleteMember")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteMemberInFamily(@RequestBody Member request){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.deleteMember(request));
    }

}
    

