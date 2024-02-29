package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EditorDao;
import exception.JaniesException;
import model.Editor;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if ("login".equals(request.getParameter("action"))) {

			String loginId = request.getParameter("loginid");
			String loginPassword = request.getParameter("loginPassword");
			String nextPage = null;

			try {
				EditorDao editorDao = new EditorDao();
				Editor editor = editorDao.doLogin(loginId, loginPassword);

				HttpSession session = request.getSession();
				session.setAttribute("editor", editor);

				nextPage = "JaniesServlet";

			} catch (JaniesException e) {
				String message = e.getMessage();
				request.setAttribute("message", message);
				request.setAttribute("error", "true");
				nextPage = "login.jsp";
			}

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションの情報を破棄
		HttpSession session = request.getSession(false);
		session.invalidate();

		// login.jspに表示するメッセージをセット
		request.setAttribute("message", "ログアウトしました");

		// login.jspを表示
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
		requestDispatcher.forward(request, response);
	}

}
