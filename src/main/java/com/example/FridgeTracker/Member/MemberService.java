package com.example.FridgeTracker.Member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;


@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository,UserRepository userRepository){
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }


    public ResponseEntity<String> addMemberToFamily( MemberRequest request){

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
