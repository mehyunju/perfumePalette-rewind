<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
    <form action="/member/login" method="post">

        <c:choose>
            <c:when test="${sessionScope.mbtiResult ne null}">
                <input type="hidden" name="returnUrl" value="/member/mbtiResult">
            </c:when>
            <c:otherwise>
                <input type="hidden" name="returnUrl" value="">
            </c:otherwise>
        </c:choose>

        <h1>로그인</h1>

        <input type="text" name="memberId" placeholder="아이디를 입력해주세요" required autofocus>
        <br>
        <input type="password" name="memberPw" placeholder="비밀번호를 입력해주세요" required>
        <br><br>

        <a href="/member/findId">아이디 찾기</a> |
        <a href="/member/findPw">비밀번호 찾기</a>
        <br><br>

        <button type="submit">로그인</button>
        <button type="button" onclick="location.href='/member/enroll'">회원가입</button>
    </form>
</body>
</html>