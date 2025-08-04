package com.prac.perfumePalette.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository mRepository;

    // 회원가입
    @Override
    public int insertMember(Member member) {
        int result = mRepository.insertMember(member);
        return result;
    }

    // 아이디 유효성 검사
    @Override
    public int checkId(String memberId) {
        int result = mRepository.checkId(memberId);
        return result;
    }

    // 닉네임 유효성 검사
    @Override
    public int checkNickname(String memberNickname) {
        int result = mRepository.checkNickname(memberNickname);
        return result;
    }

    // 이메일 유효성 검사
    @Override
    public int checkEmail(String memberEmail) {
        int result = mRepository.checkEmail(memberEmail);
        return result;
    }

    // 로그인
    @Override
    public Member login(Member member) {
        Member loginUser = mRepository.login(member);
        return loginUser;
    }

    // 아이디 찾기
    @Override
    public Member findId(Member member) {
        Member getUser = mRepository.findId(member);
        return getUser;
    }


}
