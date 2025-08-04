<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
<body>
    <form action="/member/enroll" method="post" autocomplete="off" onsubmit="return totalChk();">
        <h1>회원가입</h1>

        <label>아이디 *</label>
        <input type="text" name="memberId" placeholder="아이디를 입력해주세요" autofocus onchange="chkId()">
        <div>
            <span id="id_ok">사용 가능한 아이디입니다</span>
            <span id="id_not_ok2">영문 대소문자, 숫자만 사용 가능하며 최소 길이 4, 최대 길이 20입니다.</span>
            <span id="id_not_ok3">중복된 아이디입니다. 새로운 아이디를 입력하세요.</span>
        </div>
        <br>
        <label>닉네임 *</label>
        <input type="text" name="memberNickname" placeholder="닉네임을 입력해주세요" onchange="chkNickname()">
        <div>
            <span id="nickName_ok">사용 가능한 닉네임입니다</span>
            <span id="nickName_not_ok2">영문 대소문자, 숫자, 한글만 사용 가능하며 최소 길이 2, 최대 길이 20입니다.</span>
            <span id="nickName_not_ok3">중복된 닉네임입니다. 새로운 닉네임을 입력하세요.</span>
        </div>
        <br>
        <label>비밀번호 *</label>
        <input type="password" name="memberPw" placeholder="비밀번호를 입력해주세요">
        <br><br>
        <label>비밀번호 확인 *</label>
        <input type="password" name="reMemberPw" placeholder="비밀번호를 한번 더 입력해주세요" onchange="chkPw()">
        <div>
            <span id="pw_ok">사용 가능한 비밀번호입니다.</span>
            <span id="pw_not_ok2">비밀번호가 일치하지 않습니다.</span>
            <span id="pw_not_ok3">비밀번호는 영문 대소문자, 숫자 조합으로 8~20자리 사용해야 합니다.</span>
        </div>
        <br>
        <label>이름 *</label>
        <input type="text" name="memberName" placeholder="이름을 입력해 주세요">
        <br><br>
        <label>이메일 *</label>
        <input type="text" name="memberEmail" placeholder="6nammae@gmail.com" onchange="chkEmail()">
        <div>
            <span id="email_ok">사용 가능한 이메일입니다</span>
            <span id="email_not_ok2">최대 길이는 50자입니다.</span>
            <span id="email_not_ok3">이메일 형식이 올바르지 않습니다.</span>
            <span id="email_not_ok4">중복된 이메일입니다. 새로운 이메일을 입력하세요.</span>
        </div>
        <br>
        <label>휴대번호 *</label>
        <input type="tel" name="memberPhone" placeholder="숫자만 입력해주세요.">
        <br><br>
        <label>주소 *</label>
        <input id="address" type="text" name="memberAddr" placeholder="주소를 검색해주세요">
        <input id="detailAddress" type="text" name="memberDetailAddr" placeholder="상세주소를 입력해주세요">
        <input id="postcode" name="postcode" type="hidden">
        <button type="button" onclick="sample4_execDaumPostcode()">주소찾기</button>
        <br><br>
        <button type="submit"><span>가입하기</span></button>
    </form>

    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <script type="text/javascript">
        /* 아이디 중복 확인 */
        var idChkNum = -100;
        function chkId() {
            var memberId = $('input[name=memberId]').val();
            $.ajax({
                url : '/member/idChk',
                type : 'post',
                data : {
                    memberId : memberId
                },
                success : function(data) { // 컨트롤러에서 넘어온 result값을 받음
                    if (data == 0) {
                        // 사용가능
                        $('#id_ok').css("display", "inline-block"); // 추가: 성공 메시지 표시
                        $('#id_not_ok2').css("display", "none");
                        $('#id_not_ok3').css("display", "none");
                        idChkNum = data;
                    } else if (data == -2) {
                        // 형식오류
                        $('#id_ok').css("display", "none");
                        $('#id_not_ok2').css("display", "inline-block");
                        $('#id_not_ok3').css("display", "none");
                        idChkNum = data;
                    } else if (data > 0) {
                        // 중복
                        $('#id_ok').css("display", "none");
                        $('#id_not_ok2').css("display", "none");
                        $('#id_not_ok3').css("display", "inline-block");
                        $('input[name=memberId]').val('');
                        idChkNum = data;
                    }
                },
                error : function() {
                    alert("에러발생");
                }
            });
        };

        /* 닉네임 중복 확인 */
        var nicknameChkNum = -100;
        function chkNickname() {
            var memberNickname = $('input[name=memberNickname]').val();
            $.ajax({
                url : '/member/nicknameChk',
                type : 'post',
                data : {
                    memberNickname : memberNickname
                },
                success : function(data) {
                    if (data == 0) {
                        $('#nickName_ok').css("display", "inline-block"); // 추가: 성공 메시지 표시
                        $('#nickName_not_ok2').css("display", "none");
                        $('#nickName_not_ok3').css("display", "none");
                        nicknameChkNum = data;
                    } else if (data == -2) {
                        $('#nickName_ok').css("display", "none");
                        $('#nickName_not_ok2').css("display", "inline-block");
                        $('#nickName_not_ok3').css("display", "none");
                        nicknameChkNum = data;
                    } else if (data > 0) {
                        $('#nickName_ok').css("display", "none");
                        $('#nickName_not_ok2').css("display", "none");
                        $('#nickName_not_ok3').css("display", "inline-block");
                        $('input[name=memberNickname]').val('');
                        nicknameChkNum = data;
                    }
                },
                error : function() {
                    alert("에러발생");
                }
            });
        };

        /* 비밀번호 확인 */
        var pwChkNum = -100;
        function chkPw() {
            var memberPw = $('input[name=memberPw]').val();
            var reMemberPw = $('input[name=reMemberPw]').val();
            $.ajax({
                url : '/member/pwChk',
                type : 'post',
                data : {
                    memberPw : memberPw,
                    reMemberPw : reMemberPw
                },
                success : function(data) {
                    if (data == 0) {
                        $('#pw_ok').css("display", "inline-block"); // 추가: 성공 메시지 표시
                        $('#pw_not_ok2').css("display", "none");
                        $('#pw_not_ok3').css("display", "none");
                        pwChkNum = data;
                    } else if (data == -2) {
                        $('#pw_not_ok2').css("display", "none");
                        $('#pw_not_ok3').css("display", "inline-block");
                        $('input[name=memberPw]').focus();
                        pwChkNum = data;
                    } else if (data > 0) {
                        $('#pw_not_ok2').css("display", "inline-block");
                        $('#pw_not_ok3').css("display", "none");
                        $('input[name=reMemberPw]').focus();
                        pwChkNum = data;
                    }
                },
                error : function() {
                alert("에러발생");
                }
            });
        };

        /* 이메일 중복 확인 */
        var emailChkNum = -100;
        function chkEmail() {
            var memberEmail = $('input[name=memberEmail]').val();
            $.ajax({
                url : '/member/emailChk',
                type : 'post',
                data : {
                    memberEmail : memberEmail
                },
                success : function(data) {
                    if (data == 0) {
                        $('#email_ok').css("display", "inline-block"); // 추가: 성공 메시지 표시
                        $('#email_not_ok2').css("display", "none");
                        $('#email_not_ok3').css("display", "none");
                        $('#email_not_ok4').css("display", "none");
                        emailChkNum = data;
                    } else if (data == -2) {
                        $('#email_ok').css("display", "none");
                        $('#email_not_ok2').css("display", "inline-block");
                        $('#email_not_ok3').css("display", "none");
                        $('#email_not_ok4').css("display", "none");
                        emailChkNum = data;
                    } else if (data == -3) {
                        $('#email_ok').css("display", "none");
                        $('#email_not_ok2').css("display", "none");
                        $('#email_not_ok3').css("display", "inline-block");
                        $('#email_not_ok4').css("display", "none");
                        emailChkNum = data;
                    } else if (data > 0) {
                        $('#email_ok').css("display", "none");
                        $('#email_not_ok2').css("display", "none");
                        $('#email_not_ok3').css("display", "none");
                        $('#email_not_ok4').css("display", "inline-block");
                        $('input[name=memberEmail]').val('');
                        emailChkNum = data;
                    }
                },
                error : function() {
                    alert("에러발생");
                }
            });
        };

        /* 다음 주소 api 메서드 */
        function sample4_execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var addr = '';         // 주소 변수
                    var extraAddr = '';    // 참고항목 변수

                    // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                    if (data.userSelectedType === 'R') {
                        // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                    } else {
                        // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.jibunAddress;
                    }

                    // 사용자가 선택한 주소가 도로명 타입일 때 참고항목을 조합한다.
                    if (data.userSelectedType === 'R') {
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                            extraAddr += data.bname;
                        }

                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if (data.buildingName !== '' && data.apartment === 'Y') {
                            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }

                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if (extraAddr !== '') {
                            extraAddr = ' (' + extraAddr + ')';
                        }

                        // 조합된 참고항목을 해당 필드에 넣는다.
                        document.getElementById("address").value = extraAddr;
                    } else {
                        document.getElementById("address").value = '';
                    }

                    // 우편번호 + 주소(도로명/지번) + 참고항목
                    document.getElementById('postcode').value = data.zonecode;
                    document.getElementById("address").value = addr + extraAddr;

                    // 커서 포커스를 상세주소 입력칸으로 이동
                    document.getElementById("detailAddress").focus();
                }
            }).open();
        }

        /* 유효성 체크 통과시 회원가입이 가능하게함 */
        function totalChk() {
            var memberId = $("input[name=memberId]");
            var memberNickname = $("input[name=memberNickname]");
            var memberPw = $("input[name=memberPw]");
            var reMemberPw = $("input[name=reMemberPw]");
            var memberName = $("input[name=memberName]");
            var memberEmail = $("input[name=memberEmail]");
            var memberPhone = $("input[name=memberPhone]");
            var memberAddress = $("#address");
            var memberDetailAddress = $("#detailAddress");

            if (memberId.val() == '') {
                alert("아이디를 입력하세요.");
                memberId.focus();
                return false;
            }

            if (memberNickname.val() == '') {
                alert("닉네임을 입력하세요.");
                memberNickname.focus();
                return false;
            }

            if (memberPw.val() == '') {
                alert("비밀번호를 입력하세요.");
                memberPw.focus();
                return false;
            }
            if (reMemberPw.val() == '') {
                alert("비밀번호를 한번 더 입력하세요.");
                reMemberPw.focus();
                return false;
            }
            if (memberPw.val() != reMemberPw.val()) {
                alert("비밀번호가 일치하지 않습니다.");
                reMemberPw.focus();
                return false;
            }

            if (memberName.val() == '') {
                alert("이름을 입력하세요.");
                memberName.focus();
                return false;
            } else if (memberName.val().length < 2 || memberName.val().length > 20) {
                alert("이름은 2~20자여야 합니다.");
                memberName.focus();
                return false;
            }

            if (memberEmail.val() == '') {
                alert("이메일 주소를 입력하세요.");
                memberEmail.focus();
                return false;
            }

            if (memberPhone.val() == '') {
                alert("휴대번호를 입력하세요.");
                memberPhone.focus();
                return false;
            } else if (!/^(01[016789]{1})[0-9]{3,4}[0-9]{4}$/.test(memberPhone.val())) {
                alert("휴대번호가 올바르지 않습니다.");
                memberPhone.focus();
                return false;
            }

            if (memberAddress.val() == '') {
                alert("주소를 입력하세요.");
                memberAddress.focus();
                return false;
            }
            if (memberDetailAddress.val() == '') {
                alert("상세주소를 입력하세요.");
                memberDetailAddress.focus();
                return false;
            }

            if (idChkNum != 0) {
                alert("사용 불가능한 아이디입니다.");
                memberId.focus();
                return false;
            }

            if (nicknameChkNum != 0) {
                alert("사용 불가능한 닉네임입니다.");
                memberNickname.focus();
                return false;
            }

            if (emailChkNum != 0) {
                alert("사용 불가능한 이메일입니다.");
                memberEmail.focus();
                return false;
            }

            if (pwChkNum != 0) {
                alert("비밀번호를 다시 확인하세요.");
                memberPw.focus();
                return false;
            }

            // 모두 통과해야 제출 가능
            return true;
        }
    </script>
</body>
</html>