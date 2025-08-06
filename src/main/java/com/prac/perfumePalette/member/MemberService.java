package com.prac.perfumePalette.member;

public interface MemberService {

    int insertMember(Member member);

    int checkId(String memberId);

    int checkNickname(String memberNickname);

    int checkEmail(String memberEmail);

    Member login(Member member);

    Member findId(Member member);

    Member findPw(Member member);

    int updatePw(Member member);
}
