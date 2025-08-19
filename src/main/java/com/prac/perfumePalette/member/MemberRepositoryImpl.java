package com.prac.perfumePalette.member;

import com.prac.perfumePalette.order.OrderDetail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    // 주문내역조회
    @Override
    public List<OrderDetail> orderList(Member member) {
        return sql.selectList("memberMapper.orderList", member);
    }

    // 내 정보 - 회원정보가져오기
    @Override
    public Member selectMemberById(String memberId) {
        return sql.selectOne("memberMapper.selectMemberById", memberId);
    }

    // 내 정보 수정
    @Override
    public int changeMyInfo(Member member) {
        return sql.update("memberMapper.changeMyInfo", member);
    }

    // 정보수정 후 세션갱신
    @Override
    public Member selectOne(String memberId) {
        return sql.selectOne("memberMapper.selectOne", memberId);
    }

    // 탈퇴 비밀번호 확인
    @Override
    public Member checkPw(Member member) {
        return sql.selectOne("memberMapper.checkPw", member);
    }

    // 회원 탈퇴
    @Override
    public int bye(Member member) {
        return sql.update("memberMapper.bye", member);
    }

}
