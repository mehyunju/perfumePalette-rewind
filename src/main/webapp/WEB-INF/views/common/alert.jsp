<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림</title>
</head>
<body>

    <script type="text/javascript">
        alert('${alert.msg }')
        location.href = '${alert.url }'
    </script>

</body>
</html>