package com.example.FridgeTracker.Member;

import lombok.Data;

@Data
public class MemberRequest {
    private Member member;
    private String familyEmail;
}
