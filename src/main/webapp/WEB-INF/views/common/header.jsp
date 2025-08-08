<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
        <h2>${member.memberName }님</h2>

        <button onclick="location.href='/'">홈</button>
        <button onclick="location.href='/board/list'">게시판</button>
        <button onclick="location.href='/member/orderList'">주문내역</button>
        <button onclick="location.href='/member/myPage'">마이페이지</button>
        <button onclick="location.href='/member/logout'">로그아웃</button>
</body>
</html>