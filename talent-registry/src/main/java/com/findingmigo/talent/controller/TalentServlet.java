package com.findingmigo.talent.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.findingmigo.core.exception.AppException;
import com.findingmigo.talent.dao.TalentDao;
import com.findingmigo.talent.model.Talent;

/**
 * タレント情報の制御を行うServlet。
 */
@WebServlet("/talents")
public class TalentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            TalentDao dao = new TalentDao();
            if ("list".equals(action)) {
                List<Talent> list = dao.findAll();
                request.setAttribute("talentList", list);
                forward(request, response, "list.jsp");
            } else if ("detail".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                Talent talent = dao.findById(id);
                request.setAttribute("talent", talent);
                forward(request, response, "detail.jsp");
            }
        } catch (AppException e) {
            handleError(request, response, e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            TalentDao dao = new TalentDao();
            if ("save".equals(action)) {
                Talent talent = new Talent();
                String idStr = request.getParameter("id");
                if (idStr != null && !idStr.isEmpty()) talent.setId(Long.parseLong(idStr));
                talent.setName(request.getParameter("name"));
                talent.setBirthday(Date.valueOf(request.getParameter("birthday")));
                talent.setJoinDate(Date.valueOf(request.getParameter("joinDate")));
                talent.setHomeTown(request.getParameter("homeTown"));
                talent.setBloodType(request.getParameter("bloodType"));
                talent.setAge(Integer.parseInt(request.getParameter("age")));
                talent.setMemberColor(request.getParameter("memberColor"));
                
                dao.save(talent);
                response.sendRedirect("talents?action=list");
            } else if ("delete".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                dao.delete(id);
                response.sendRedirect("talents?action=list");
            }
        } catch (AppException e) {
            handleError(request, response, e);
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException {
        request.setAttribute("message", e.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}
