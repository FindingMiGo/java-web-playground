package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JaniesDao;
import exception.JaniesException;
import model.Janies;

@WebServlet("/JaniesServlet")
public class JaniesServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String searchName = request.getParameter("searchname");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String joinDate = request.getParameter("joinDate");
		String homeTown = request.getParameter("homeTown");
		String bloodType = request.getParameter("bloodType");
		String age = request.getParameter("age");
		String memberColor = request.getParameter("memberColor");

		String deleteButton = request.getParameter("delete");
		String register = request.getParameter("register");
		String reset = request.getParameter("reset");

		String nextPage = null;
		try {
			JaniesDao janiesDao = new JaniesDao();
			List<Janies> janiesList = null;

			if(register != null) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("detail.jsp");
				requestDispatcher.forward(request, response);
			}
			else if (reset != null) {
				janiesDao.resetJanies();
				janiesList = janiesDao.findAllJanies();

			} else if (deleteButton != null) {
				janiesDao.deleteJanies(Long.parseLong(id));
				janiesList = janiesDao.findAllJanies();
			} else if (name != null) {
				Janies janies = new Janies(id, name, birthday, joinDate, homeTown, bloodType, age, memberColor);
				janiesDao.registerJanies(janies);
				janiesList = janiesDao.findAllJanies();

			} else if (searchName != null) {
				janiesList = janiesDao.findJanies(searchName);
			} else {
				janiesList = janiesDao.findAllJanies();
			}

			request.setAttribute("janiesList", janiesList);

			nextPage = "list.jsp";

		} catch (JaniesException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");

			// ログイン画面を表示する準備
			nextPage = "login.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 次の画面に遷移
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String id = request.getParameter("janies_id");

		String nextPage = null;
		try {
			JaniesDao janiesDao = new JaniesDao();
			Janies janiesList = janiesDao.findJanies(Integer.parseInt(id));

			request.setAttribute("janies", janiesList);

			nextPage = "detail.jsp";

		} catch (JaniesException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");

			nextPage = "login.jsp";
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (nextPage == null) {
			nextPage = "login.jsp";
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}