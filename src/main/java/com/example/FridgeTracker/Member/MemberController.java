package com.example.FridgeTracker.Member;


import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.Commands.CommandInvoker;
import com.example.FridgeTracker.Commands.MemberCommands.AddMemberCommand;
import com.example.FridgeTracker.Commands.MemberCommands.DeleteMemberCommand;
import com.example.FridgeTracker.Commands.MemberCommands.GetMemberCommand;
import com.example.FridgeTracker.User.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MemberController {

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    public MemberController(UserRepository userRepository, MemberRepository memberRepository){
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
    }

    // Temporary add member endpoint without error control
    @PostMapping("/addMember")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> addMemberToFamily(@RequestBody MemberRequest request){
        Command createCommand = new AddMemberCommand(request,userRepository, memberRepository);
        CommandInvoker invoker = new CommandInvoker(createCommand);
        return invoker.executeCommand();
    }

    //GET MEMBER
    @GetMapping("/member/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getMember(@PathVariable UUID id){
        Command createCommand = new GetMemberCommand(id, memberRepository);
        CommandInvoker invoker = new CommandInvoker(createCommand);
        return invoker.executeCommand();
    }

    //UPDATE MEMBER IN FAMILY
    @PostMapping("/updateMember")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> updateMemberInFamily(@RequestBody MemberRequest request){
        Command createCommand = new AddMemberCommand(request,userRepository, memberRepository);
        CommandInvoker invoker = new CommandInvoker(createCommand);
        return invoker.executeCommand();
    }


    //DELETE MEMBER FROM FAMILY
    @PostMapping("/deleteMember")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteMemberInFamily(@RequestBody Member request){
        Command createCommand = new DeleteMemberCommand(request, memberRepository);
        CommandInvoker invoker = new CommandInvoker(createCommand);
        return invoker.executeCommand();
    }

}
    

