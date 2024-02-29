<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインフォーム</title>

<!-- Bootstrap CSS をインクルード -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
	<div class="container">
		<h2 class="mt-5">ログインフォーム</h2>
		<%
		// 処理メッセージとエラー判定を取得
		String message = (String) request.getAttribute("message");
		String error = (String) request.getAttribute("error");

		// 正常終了した場合のメッセージを表示
		if (message != null && error == null) {
		%>
		<div class="alert alert-success mt-3" role="alert">
			<%=message%>
		</div>
		<%
		// 異常終了した場合のメッセージを表示
		} else if (message != null && error != null) {
		%>
		<div class="alert alert-danger mt-3" role="alert">
			<%=message%>
		</div>
		<%
		}
		%>
		<form action="Login" method="post" class="mt-3">
			<div class="form-group">
				<label for="loginid">ユーザー名:</label> <input type="text" class="form-control" name="loginid" value="moriyama" required>
			</div>
			<div class="form-group">
				<label for="loginPassword">パスワード:</label> <input type="password" class="form-control" name="loginPassword" value="moriyama" required>
			</div>
			<button type="submit" name="action" value="login" class="btn btn-primary">ログイン</button>
		</form>
	</div>

	<!-- Bootstrap JavaScript をインクルード -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>