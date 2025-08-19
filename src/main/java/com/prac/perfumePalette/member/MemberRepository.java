package com.prac.perfumePalette.member;

import com.prac.perfumePalette.order.OrderDetail;

import java.util.List;

public interface MemberRepository {

    int insertMember(Member member);

    int checkId(String memberId);

    int checkNickname(String memberNickname);

    int checkEmail(String memberEmail);

    Member login(Member member);

    Member findId(Member member);

    Member findPw(Member member);

    int updatePw(Member member);

    List<OrderDetail> orderList(Member member);

    Member selectMemberById(String memberId);

    Member checkPw(Member member);

    int bye(Member member);
}
