<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<tr>
	<td>顧客名:</td>
	<td>${param.customerName}</td>
	<input type="hidden" name="customerName" value="${param.customerName}"
		readonly>
</tr>
<tr>
	<td>電話番号:</td>
	<td>${param.customerTelno}</td>
	<input type="hidden" name="customerTelno"
		value="${param.customerTelno}" readonly>
</tr>
<tr>
	<td>郵便番号:</td>
	<td>${param.customerPostalcode}</td>
	<input type="hidden" name="customerPostalcode"
		value="${param.customerPostalcode}" readonly>
</tr>
<tr>
	<td>住所:</td>
	<td>${param.customerAddress}</td>
	<input type="hidden" name="customerAddress"
		value="${param.customerAddress}" readonly>
</tr>
<tr>
	<td>割引率:</td>
	<td>${param.discountRate}</td>
	<input type="hidden" name="discountRate" value="${param.discountRate}"
		readonly>
</tr>
