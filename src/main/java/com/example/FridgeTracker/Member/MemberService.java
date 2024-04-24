package com.example.FridgeTracker.Member;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;

import jakarta.transaction.Transactional;


@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository,UserRepository userRepository){
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }

    //ADD MEMBER TO FAMILY
    public ResponseEntity<String> addMemberToFamily(MemberRequest request){

        Optional<User> user = userRepository.findById(request.getUserID());

        if (user != null){

            Member member = request;

            member.setUser(user); // Set the owner (family) of the member
  
              // Save the member to the repository
            memberRepository.save(member);

            return ResponseEntity.ok("Member added successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to add fridge");
        }
    }

    //GET MEMBER
    public ResponseEntity<Member> getMember(UUID id){

        Optional<Member> member = memberRepository.findById(id);
       
       if(member != null){
           return ResponseEntity.ok(member.get()); 
       } else {
           return ResponseEntity.status(600).body(null); 
       }
   }


   //UPDATE MEMBER IN FAMILY
   public ResponseEntity<String> updateMemberInFamily(MemberRequest request){
   
    Optional<User> userOptional = userRepository.findById(request.getUserID());

    if(userOptional.isPresent()){
        User family = userOptional.get();

        Optional<Member> memberOptional = family.getMembers().stream()
                                .filter(member -> member.getId().equals(request.getId()))
                                .findFirst();

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            if(request.getName() != null){
                member.setName(request.getName());
            }
            if(request.getAge() > 0){
                member.setAge(request.getAge());
            }
            if(request.getImageURL() != null){
                member.setImageURL(request.getImageURL());
            }
            if(request.getHeight() > 0){
                member.setHeight(request.getHeight());
            }
            if(request.getWeight() > 0){
                member.setWeight(request.getWeight());
            }
            if(request.getAllergies() != null && !request.getAllergies().isEmpty()){
                member.setAllergies(request.getAllergies());
            }
            if(request.getPreference() != null && !request.getPreference().isEmpty()){
                member.setPreference(request.getPreference());
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

    @Transactional
    public String deleteMember(Member request){
        Optional<Member> optionalMember = memberRepository.findById(request.getId());
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            memberRepository.delete(member);
            return "Successfully deleted Member";
        } else {
            return "Failed to delete Member";
        }

    }

    
}
