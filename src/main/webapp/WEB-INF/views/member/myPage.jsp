<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>𝑷𝒆𝒓𝒇𝒖𝒎𝒆 𝑷𝒂𝒍𝒆𝒕𝒕𝒆</title>
</head>
<body>
    <jsp:include page="../common/header.jsp" />

    <h1>마이페이지</h1>

    <button onclick="location.href='/member/changeMyInfo'">내 정보 수정</button>
    <button onclick="location.href='/member/myBoard'">작성게시글</button>
    <button onclick="location.href='/member/myComment'">작성댓글</button>
    <button onclick="location.href='/member/bye'">회원탈퇴</button>
</body>
</html
