package com.example.FridgeTracker.Commands.MemberCommands;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.Member.Member;
import com.example.FridgeTracker.Member.MemberRepository;
import com.example.FridgeTracker.Member.MemberRequest;
import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;

public class AddMemberCommand implements Command{

    private UserRepository userRepository;
    private MemberRequest request;
    private MemberRepository memberRepository;

    @Autowired
    public AddMemberCommand(MemberRequest request, UserRepository userRepository, MemberRepository memberRepository){
        this.request = request;
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public ResponseEntity<?> execute() {

        Optional<User> user = userRepository.findById(request.getUserID());

        if (user != null){

            Member member = request.getMember();

            member.setUser(user); // Set the owner (family) of the member
  
              // Save the member to the repository
            memberRepository.save(member);

            return ResponseEntity.ok("Member added successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to add fridge");
        }
    }
    
}
