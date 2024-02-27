package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import exception.JsysException;
import model.Customer;








@WebServlet("/search_customer")
public class SearchCustomerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String searchName = request.getParameter("search");
		String nextPage = "list_customer.jsp";

		if (searchName == null) {
			nextPage = "search.jsp";
		} else {
			try {
				CustomerDao customerDao = new CustomerDao();
				List<Customer> customerList = null;

				customerList = customerDao.findCustomer(searchName);

				request.setAttribute("customerList", customerList);

			} catch (JsysException e) {
				String message = e.getMessage();
				request.setAttribute("message", message);
				request.setAttribute("error", "true");
			}
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
