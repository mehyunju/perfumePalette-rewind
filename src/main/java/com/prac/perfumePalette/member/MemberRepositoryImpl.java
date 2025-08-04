package com.prac.perfumePalette.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepository{

    @Autowired
    private SqlSession sql;

    // 회원가입
    @Override
    public int insertMember(Member member) {
        int result = sql.insert("memberMapper.insertMember", member);
        return result;
    }

    // 아이디 유효성 검사
    @Override
    public int checkId(String memberId) {
        int result = sql.selectOne("memberMapper.checkId", memberId);
        return result;
    }

    // 닉네임 유효성 검사
    @Override
    public int checkNickname(String memberNickname) {
        int result = sql.selectOne("memberMapper.checkNickname", memberNickname);
        return result;
    }

    // 이메일 유효성 검사
    @Override
    public int checkEmail(String memberEmail) {
        int result = sql.selectOne("memberMapper.checkEmail", memberEmail);
        return result;
    }

    // 로그인
    @Override
    public Member login(Member member) {
        Member loginUser = sql.selectOne("memberMapper.login", member);
        return loginUser;
    }

    // 아이디 찾기
    @Override
    public Member findId(Member member) {
        Member getUser = sql.selectOne("memberMapper.findId", member);
        return getUser;
    }
}
