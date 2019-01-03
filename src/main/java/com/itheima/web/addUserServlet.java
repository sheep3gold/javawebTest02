package com.itheima.web;

import com.itheima.beans.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addUserServlet")
public class addUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("UTF-8");
        UserService userService = new UserService();
//        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String address = request.getParameter("address");
        String qq = request.getParameter("qq");
        String email = request.getParameter("email");
//        int i = Integer.parseInt(id);
        int a = Integer.parseInt(age);
        User user = new User(name, sex, a, address, qq, email);
        List<User> list = userService.queryByName(name);
        if (null != list && list.size() > 0) {
            request.setAttribute("addError", "用户名已存在");
            request.getRequestDispatcher("/add.jsp").forward(request, response);
            return;
        }
        boolean b = userService.addUser(user);
        if (b) {
            response.sendRedirect("FindUserByPageServlet");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
