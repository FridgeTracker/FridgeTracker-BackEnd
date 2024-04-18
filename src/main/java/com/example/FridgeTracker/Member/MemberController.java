package com.example.FridgeTracker.Member;

import java.util.Optional;
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
    public ResponseEntity<String> addMemberToFamily(@RequestBody MemberRequest request){

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


    @GetMapping("/member/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Member> getMember(@PathVariable UUID id){

         Optional<Member> member = memberRepository.findById(id);
        
        if(member != null){
            return ResponseEntity.ok(member.get()); 
        } else {
            return ResponseEntity.status(600).body(null); 
        }
    }


    @PostMapping("/updateMember")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> updateMemberInFamily(@RequestBody MemberRequest request){
   
        Optional<User> userOptional = userRepository.findById(request.getUserID());

        if(userOptional.isPresent()){
            User family = userOptional.get();

            Optional<Member> memberOptional = family.getMembers().stream()
                                    .filter(member -> member.getId().equals(request.getMember().getId()))
                                    .findFirst();

            if (memberOptional.isPresent()) {
                Member member = memberOptional.get();

                if(request.getMember().getName() != null){
                    member.setName(request.getMember().getName());
                }
                if(request.getMember().getAge() > 0){
                    member.setAge(request.getMember().getAge());
                }
                if(request.getMember().getImageURL() != null){
                    member.setImageURL(request.getMember().getImageURL());
                }
                if(request.getMember().getHeight() > 0){
                    member.setHeight(request.getMember().getHeight());
                }
                if(request.getMember().getWeight() > 0){
                    member.setWeight(request.getMember().getWeight());
                }
  
                userRepository.save(family);

                return ResponseEntity.ok("Member updated successfully");
            } else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member could not be foundd in the family");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to find user account");
        }
    }

    }
    

