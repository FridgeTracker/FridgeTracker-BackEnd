package com.example.FridgeTracker.Member;

import java.util.UUID;

import lombok.Data;

@Data
public class MemberRequest {
    private Member member;
    private UUID userID;
}

/* Send request like

  {
    "member":{
        "name":"ranko",
        "age":5,
        "allergies":"yes",
        "height":100,
        "weight":50
        },
    "familyEmail":"r@g.c"
}
 */