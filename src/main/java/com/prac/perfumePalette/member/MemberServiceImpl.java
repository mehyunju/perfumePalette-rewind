package com.prac.perfumePalette.member;

import com.prac.perfumePalette.order.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository mRepository;

    // 회원가입
    @Override
    public int insertMember(Member member) {
        return mRepository.insertMember(member);
    }

    // 아이디 유효성 검사
    @Override
    public int checkId(String memberId) {
        return mRepository.checkId(memberId);
    }

    // 닉네임 유효성 검사
    @Override
    public int checkNickname(String memberNickname) {
        return mRepository.checkNickname(memberNickname);
    }

    // 이메일 유효성 검사
    @Override
    public int checkEmail(String memberEmail) {
        return mRepository.checkEmail(memberEmail);
    }

    // 로그인
    @Override
    public Member login(Member member) {
        return mRepository.login(member);
    }

    // 아이디 찾기
    @Override
    public Member findId(Member member) {
        return mRepository.findId(member);
    }

    // 비밀번호 찾기
    @Override
    public Member findPw(Member member) {
        return mRepository.findPw(member);
    }

    // 비밀번호 변경
    @Override
    public int updatePw(Member member) {
        return mRepository.updatePw(member);
    }

    // 주문내역조회 + 결제금액 계산
    @Override
    public List<OrderDetail> orderList(Member member) {

        // 회원 번호를 기준으로 주문 내역 리스트 조회
        List<OrderDetail> list = mRepository.orderList(member);

        // 각 주문에 대해 결제금액 계산 (향수 가격 * 주문 수량)
        for (OrderDetail order : list) {
            order.calculateTotalPrice();
        }

        return list;
    }
}
