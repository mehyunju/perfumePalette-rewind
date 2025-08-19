<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원탈퇴</title>
</head>
<body>
    <jsp:include page="../common/header.jsp" />

    <form action="/member/bye" method="post">
        <h1>회원탈퇴</h1>

        ※ 탈퇴 시 모든 정보가 삭제됩니다. <br>
        ※ 비밀번호를 입력 후 탈퇴 버튼을 누르면 탈퇴 처리가 완료됩니다.

        <br><br>
        <input type="hidden"  name="memberId" value="${member.memberId }"/>
        <input type="password" name="memberPw" placeholder="비밀번호를 입력해주세요" />
        <br>
        <button type="submit">탈퇴하기</button>
    </form>
</body>
</html>