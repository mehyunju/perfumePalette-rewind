package com.prac.perfumePalette.member;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {
    private int memberNo;
    private String memberId;
    private String memberNickname;
    private String memberPw;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private String memberAddr;
    private String memberDetailAddr;  // 상세주소
    private String postcode;          // 우편번호
    private Timestamp memberDate;
    private Timestamp memberEndDate;
    private int memberStatus;
    private int memberType;
}
