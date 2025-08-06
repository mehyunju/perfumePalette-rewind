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
        return sql.insert("memberMapper.insertMember", member);
    }

    // 아이디 유효성 검사
    @Override
    public int checkId(String memberId) {
        return sql.selectOne("memberMapper.checkId", memberId);
    }

    // 닉네임 유효성 검사
    @Override
    public int checkNickname(String memberNickname) {
        return sql.selectOne("memberMapper.checkNickname", memberNickname);
    }

    // 이메일 유효성 검사
    @Override
    public int checkEmail(String memberEmail) {
        return sql.selectOne("memberMapper.checkEmail", memberEmail);
    }

    // 로그인
    @Override
    public Member login(Member member) {
        return sql.selectOne("memberMapper.login", member);
    }

    // 아이디 찾기
    @Override
    public Member findId(Member member) {
        return sql.selectOne("memberMapper.findId", member);
    }

    // 비밀번호 찾기
    @Override
    public Member findPw(Member member) {
        return sql.selectOne("memberMapper.findPw", member);
    }

    // 비밀번호 변경
    @Override
    public int updatePw(Member member) {
        return sql.update("memberMapper.updatePw", member);
    }
}
