<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>𝑷𝒆𝒓𝒇𝒖𝒎𝒆 𝑷𝒂𝒍𝒆𝒕𝒕𝒆</title>
</head>
<body>
    <jsp:include page="../common/header.jsp" />

	<h1>주문내역</h1>

	<table>
        <thead>
            <tr>
                <th>주문번호</th>
                <th>주문상품</th>
                <th>상품가격</th>
                <th>주문수량</th>
                <th>결제금액</th>
                <th>주문상태</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${orderList}">
                <tr>
                    <td>${order.orderNo}</td>
                    <td>${order.perfumeName}</td>
                    <td>
                        <fmt:formatNumber value="${order.perfumePrice}" pattern="#,##0"/>
                    </td>
                    <td>${order.orderQuantity}</td>
                    <td>
                        <fmt:formatNumber value="${order.totalPrice}" pattern="#,##0"/>
                    </td>
                    <td>${order.orderStatus}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>