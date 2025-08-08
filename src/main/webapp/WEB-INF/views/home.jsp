<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>𝑷𝒆𝒓𝒇𝒖𝒎𝒆 𝑷𝒂𝒍𝒆𝒕𝒕𝒆</title>
</head>
<body>
    <h1>𝑷𝒆𝒓𝒇𝒖𝒎𝒆 𝑷𝒂𝒍𝒆𝒕𝒕𝒆</h1>

    <!-- 로그인 안 했을 때 -->
        <c:if test="${sessionScope.member eq null}">
            <div>
                <button onclick="location.href='/member/enroll'">회원가입</button>
                <button onclick="location.href='/member/login'">로그인</button>
                <br><br>
                <a href="/member/findId">아이디 찾기</a>
                <a href="/member/findPw">비밀번호 찾기</a>
            </div>
        </c:if>

        <!-- 로그인 했을 때 -->
        <c:if test="${sessionScope.member ne null}">

            <h3>${member.memberName }님 환영합니다.</h3>

            <div>
                <button onclick="location.href='/board/list'">게시판</button>
                <button onclick="location.href='/member/orderList'">주문내역조회</button>
                <button onclick="location.href='/member/myPage'">마이페이지</button>
                <button onclick="location.href='/member/logout'">로그아웃</button>
            </div>
        </c:if>
</body>
</html
