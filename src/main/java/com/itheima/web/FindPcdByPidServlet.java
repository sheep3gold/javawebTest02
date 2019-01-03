package com.itheima.web;

import com.itheima.service.Impl.PcdServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/FindPcdByPidServlet")
public class FindPcdByPidServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //1.获取提交的父级区域的id
        int pid = Integer.parseInt(request.getParameter("pid"));
        PcdServiceImpl pcdService = new PcdServiceImpl();
        String json = pcdService.findPcdByPid(pid);
        response.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
