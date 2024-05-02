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

public class UpdateMemberCommand implements Command{

    private UserRepository userRepository;
    private MemberRequest request;

    @Autowired
    public UpdateMemberCommand(MemberRequest request, UserRepository userRepository){
        this.request = request;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> execute() {

        Optional<User> userOptional = userRepository.findById(request.getUserID());

    if(userOptional.isPresent()){
        User family = userOptional.get();

        Optional<Member> memberOptional = family.getMembers().stream()
                                .filter(member -> member.getId().equals(request.getMember().getId()))
                                .findFirst();

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            updateMemberFields(member);
            userRepository.save(family);

            return ResponseEntity.ok("Member updated successfully");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member could not be foundd in the family");
        }
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to find user account");
    }

    }

    private void updateMemberFields(Member member){

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
        if(request.getMember().getAllergies() != null && !request.getMember().getAllergies().isEmpty()){
            member.setAllergies(request.getMember().getAllergies());
        }
        if(request.getMember().getPreference() != null && !request.getMember().getPreference().isEmpty()){
            member.setPreference(request.getMember().getPreference());
        }

    }
    
}
