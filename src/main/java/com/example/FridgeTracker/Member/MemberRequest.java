package com.example.FridgeTracker.Member;

import java.util.UUID;

import lombok.Data;

@Data
public class MemberRequest {
    private Member member;
    private UUID userID;
}
