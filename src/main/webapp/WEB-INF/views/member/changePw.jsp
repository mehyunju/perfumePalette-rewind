<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 변경</title>
</head>
<body>
    <form action="/member/changePw" method="post" >
        <h1>비밀번호 변경</h1>

		<input type="hidden" name="memberId" value="${member.memberId}">

		<div class="contentBox">
			<div class="input-box">
				<label for="newPw" class="contentLabel">비밀번호</label>
				<input type="password" name="newPw" required>
			</div>

			<div class="input-box">
				<label for="confirmPw" class="contentLabel">비밀번호 확인</label>
				<input type="password" name="confirmPw">
			</div>
		</div>
		<button type="submit">비밀번호 변경</button>
	</form>
</body>
</html>