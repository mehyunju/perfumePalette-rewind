<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>아이디 출력 페이지</title>
</head>
<body>
    <h1>아이디 찾기</h1>

    <div>회원님의 아이디 찾기가 완료되었습니다.</div>

    <div class="input-box">
        <input type="text" name="memberId" value="${member.memberId} " readonly>
    </div>
    <br>
    <div class="input-btn">
        <button type="button" onclick="location.href='/member/login'">로그인</button>
    </div>
</body>
</html>