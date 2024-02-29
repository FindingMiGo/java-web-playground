<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Customer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>メンバー一覧</title>
    <!-- Bootstrap CSSをリンク -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4 text-center">
    <h1 class="mb-4">得意先一覧</h1>

    <table class="table table-striped table-bordered mx-auto">
        <thead class="thead-dark">
            <tr>
                <th>コード</th>
                <th>名前</th>
                <th>電話番号</th>
                <th>郵便番号</th>
                <th>住所</th>
                <th>割引率</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="customer" items="${customerList}">
                <tr>
                    <td>${customer.customerCode}</td>
                    <td>${customer.customerName}</td>
                    <td>${customer.customerTelno}</td>
                    <td>${customer.customerPostalcode}</td>
                    <td>${customer.customerAddress}</td>
                    <td>${customer.discountRate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- "topへ戻る"ボタンを中央寄せ -->
    <div class="text-center">
        <a href="index.html" class="btn btn-secondary mt-3">topへ戻る</a>
    </div>
</div>

<!-- Bootstrap JavaScriptをリンク -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
