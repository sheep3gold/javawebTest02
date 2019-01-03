package com.itheima.web;

import com.itheima.beans.PageBean;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/FindUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1;
        int pageSize = 5;
        String currentPageStr = request.getParameter("currentPage");
        if (currentPageStr != null && !"".equals(currentPageStr)) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        String pageSizeStr = request.getParameter("pageSize");
        if (pageSizeStr != null && !"".equals(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        UserService userService = new UserService();
        PageBean pageBean = userService.findUserByPage(currentPage, pageSize);
        request.setAttribute("pageBean", pageBean);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
