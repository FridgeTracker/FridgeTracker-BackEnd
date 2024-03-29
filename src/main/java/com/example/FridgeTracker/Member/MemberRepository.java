package com.example.FridgeTracker.Member;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    
    Optional<Member> findById(UUID id);

}
