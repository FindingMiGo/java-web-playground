<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得意先登録確認画面</title>

<!-- Bootstrap CSS を読み込む -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1 class="mt-4">得意先登録確認画面</h1>

		<!-- サーブレットにフォームデータを送信する -->
		<form action="register_customer" method="post">
			<p class="mt-4">以下の内容で登録します。よろしいですか。</p>
			<table class="table">
				<jsp:include page="customerTable.jsp" />
			</table>

			<a href="index.html" class="btn btn-secondary">キャンセル</a>
			<button type="submit" class="btn btn-primary" name="button"
				value="register">登録する</button>
		</form>
	</div>

	<!-- Bootstrap JavaScript を読み込む -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
