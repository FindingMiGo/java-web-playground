<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>受注一覧</title>
<style>
    table { width: 100%; border-collapse: collapse; margin-top: 20px; }
    th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
    th { background-color: #f2f2f2; }
    .container { width: 90%; margin: 20px auto; text-align: center; }
    .btn-back { display: inline-block; margin-top: 20px; padding: 10px 20px; text-decoration: none; background-color: #f0f0f0; border: 1px solid #ccc; color: #333; }
</style>
</head>
<body>
    <div class="container">
        <h1>受注一覧</h1>
        
        <c:if test="${not empty message}">
            <p style="color: red;">${message}</p>
        </c:if>

        <table>
            <thead>
                <tr>
                    <th>受注番号</th>
                    <th>得意先名</th>
                    <th>担当従業員</th>
                    <th>合計金額</th>
                    <th>明細数</th>
                    <th>配送予定日</th>
                    <th>受注日</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td>${order.orderNo}</td>
                        <td>${order.customerName}</td>
                        <td>${order.employeeName}</td>
                        <td><fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="¥" /></td>
                        <td>${order.detailNum}</td>
                        <td>${order.deliverDate}</td>
                        <td>${order.orderDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <a href="index.html" class="btn-back">メニューに戻る</a>
    </div>
</body>
</html>
