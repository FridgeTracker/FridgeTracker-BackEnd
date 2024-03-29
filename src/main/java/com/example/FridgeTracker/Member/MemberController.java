package com.example.FridgeTracker.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MemberController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemberRepository memberRepository;

    // Temporary add member endpoint without error control
    @PostMapping("/addMember")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> addMemberToFamily(@RequestBody MemberRequest request){

        
        User user = userRepository.findByEmail(request.getFamilyEmail());

        Member member = request.getMember();
        return ResponseEntity.status(700).body(user);

/* 
        if (user != null){

            Member member = request.getMember();

            member.setFamily(user); // Set the owner (family) of the member
  
              // Save the member to the repository
            memberRepository.save(member);

            return ResponseEntity.ok("Member added successfully");
        }
        else {
            return ResponseEntity.status(700).body("Fail to add fridge");
        }
    }*/

}
}
