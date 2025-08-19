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

    // 내 정보 - 회원정보가져오기
    @Override
    public Member selectMemberById(String memberId) {
        return mRepository.selectMemberById(memberId);
    }

    // 내 정보 수정
    @Override
    public int changeMyInfo(Member member) {
        return mRepository.changeMyInfo(member);
    }

    // 내정보수정 후 세션 갱신
    @Override
    public Member selectOne(String memberId) {
        return mRepository.selectOne(memberId);
    }

    // 탈퇴 비밀번호 확인
    @Override
    public Member checkPw(Member member) {
        return mRepository.checkPw(member);
    }

    // 회원탈퇴
    @Override
    public int bye(Member member) {
        return mRepository.bye(member);
    }
}
