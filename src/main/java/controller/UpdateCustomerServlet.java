package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import exception.JsysException;
import model.Customer;

/**
 * Servlet implementation class UpdateCustomer
 */
@WebServlet("/update_customer")
public class UpdateCustomerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String searchCode = request.getParameter("search");

		if (searchCode != null) {
			try {
				CustomerDao customerDao = new CustomerDao();
				Customer customer = customerDao.findCustomerByCustomerCode(searchCode);
				request.setAttribute("customer", customer);

			} catch (JsysException e) {
				e.printStackTrace();
			}
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("update.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String buttonId = request.getParameter("button");
		String nextPage = "confirmation.jsp";

		String customerCode = request.getParameter("customerCode");
		String customerName = request.getParameter("customerName");
		String customerTelno = request.getParameter("customerTelno");
		String customerPostalcode = request.getParameter("customerPostalcode");
		String customerAddress = request.getParameter("customerAddress");
		String sDiscountRate = request.getParameter("discountRate");

		int discountRate = 0;
		if (sDiscountRate != null && !sDiscountRate.isEmpty()) {
			try {
				discountRate = Integer.parseInt(sDiscountRate);
			} catch (NumberFormatException e) {
				discountRate = 0;
			}
		}

		switch (buttonId) {
		case "input":
			request.setAttribute("button", "update");
			request.setAttribute("buttonName", "変更する");
			request.setAttribute("heading", "得意先変更確認画面");
			request.setAttribute("attention", "以下の内容で変更します。よろしいですか。");
			request.setAttribute("customerCode", customerCode);
			nextPage = "updateConfirmation.jsp";
			break;
		case "update":
			try {
				CustomerDao customerDao = new CustomerDao();
				Customer customer = new Customer(customerCode, customerName, customerTelno, customerPostalcode,
						customerAddress,
						discountRate, false);
				System.out.println(customer.toString());
				customerDao.updateCustomer(customer);
			} catch (JsysException e) {
				String message = e.getMessage();
				request.setAttribute("message", message);
				request.setAttribute("error", "true");
			}
			request.setAttribute("customerCode", customerCode);
			nextPage = "complete.jsp";

			break;
			
		case "delete":
			request.setAttribute("button", "doDelete");
			request.setAttribute("buttonName", "削除する");
			request.setAttribute("heading", "得意先削除確認画面");
			request.setAttribute("attention", "以下の内容を削除します。よろしいですか。");
			request.setAttribute("customerCode", customerCode);
			nextPage = "updateConfirmation.jsp";
			break;
			
		case "doDelete":
			String code = request.getParameter("customerCode");
			if (code.isEmpty() || code.isBlank()) {
				response.sendRedirect("index.html");
			}

			try {
				CustomerDao customerDao = new CustomerDao();
				customerDao.deleteCustomerByCode(code);
			} catch (JsysException e) {
				String message = e.getMessage();
				request.setAttribute("message", message);
				request.setAttribute("error", "true");
			}
			nextPage = "deleteComplete.jsp";
			break;
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
