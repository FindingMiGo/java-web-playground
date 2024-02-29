<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Janies" %>
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

<div class="container mt-4">
    <h1 class="mb-4">メンバー一覧</h1>

    <form method="post" action="JaniesServlet" class="mb-3">
        <div class="form-group">
            <label for="searchName">名前で検索：</label>
            <input type="text" name="searchname" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">検索</button>
    </form>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>名前</th>
            <th>詳細</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="janies" items="${janiesList}">
                <tr>
                    <td>${janies.id}</td>
                    <td>${janies.name}</td>
                    <td><a href="JaniesServlet?janies_id=${janies.id}" class="btn btn-info">詳細</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <form method="post" action="JaniesServlet">
        <button type="submit" name="register" class="btn btn-success">新規登録</button>
        <button type="submit" name="reset" class="btn btn-danger">リセット</button>
    </form>
</div>

<!-- Bootstrap JavaScriptをリンク -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
