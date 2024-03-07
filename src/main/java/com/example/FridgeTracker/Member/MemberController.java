package com.example.FridgeTracker.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FridgeTracker.Fridge.Fridge;
import com.example.FridgeTracker.Fridge.NewFridgeBody;
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

    @PostMapping("/addMember")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addMemberToFamily(@RequestBody Member request){

        User user = userRepository.findByEmail(request.getFamily().getEmail());

        if (user != null){
            /*Member member = new Member();

            member.setAge(0);
            fridge.setFridgeName(request.get);
            fridge.setCapacity(request.getCapacity());

            fridge.setOwner(user);*/

            memberRepository.save(request);

            return ResponseEntity.ok("Fridge added successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to add fridge");
        }
    }

    
}
