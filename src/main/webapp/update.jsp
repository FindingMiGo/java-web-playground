<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Customer"%>
<%@ page import="java.sql.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>詳細</title>
<!-- Bootstrap CSSをリンク -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-4">
		<h1 class="mb-4">得意先登録</h1>

		<form method="get" action="update_customer">
			<div class="form-row align-items-center">
				<div class="col-md-8">
					<input type="text" name="search" class="form-control"
						placeholder="検索キーワード">
				</div>
				<div class="col-md-4">
					<button type="submit" class="btn btn-primary btn-block">検索</button>
				</div>
			</div>
		</form>

		<%
		Customer customer = (Customer) request.getAttribute("customer");
		%>

		<%
		if (customer != null) {
		%>
		<hr>
		<form action="update_customer" method="post">
			<div class="form-group">
				<label for="customerCode">顧客コード:</label> <input type="text"
					name="customerCode" class="form-control"
					value="${customer.customerCode}" readonly>
			</div>

			<div class="form-group">
				<label for="customerName">名前:</label> <input type="text"
					name="customerName" class="form-control"
					value="${customer.customerName}" required>
			</div>

			<div class="form-group">
				<label for="customerTelno">電話番号:</label> <input type="tel"
					name="customerTelno" class="form-control"
					value="${customer.customerTelno}">
			</div>

			<div class="form-group">
				<label for="customerPostalcode">郵便番号:</label> <input type="text"
					name="customerPostalcode" class="form-control"
					value="${customer.customerPostalcode}">
			</div>

			<div class="form-group">
				<label for="customerAddress">住所:</label> <input type="text"
					name="customerAddress" class="form-control"
					value="${customer.customerAddress}">
			</div>

			<div class="form-group">
				<label for="discountRate">割引率:</label> <input type="text"
					name="discountRate" class="form-control"
					value="${customer.discountRate}">
			</div>

			<button type="submit" name="button" value="input"
				class="btn btn-primary">登録</button>
			<button type="submit" name="button" value="delete"
				class="btn btn-primary">削除</button>
		</form>
		<%
		}
		%>
	</div>

	<!-- Bootstrap JavaScriptをリンク -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
