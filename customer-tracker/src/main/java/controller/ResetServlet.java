package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import exception.JsysException;

/**
 * Servlet implementation class ResetServlet
 */
@WebServlet("/reset")
public class ResetServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			CustomerDao CustomerDao = new CustomerDao();

			CustomerDao.resetCustomer();
		} catch (JsysException | SQLException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");
		}

		response.sendRedirect("index.html");

	}

}
