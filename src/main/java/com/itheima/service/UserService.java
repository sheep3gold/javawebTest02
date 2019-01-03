package com.itheima.service;

import com.itheima.beans.PageBean;
import com.itheima.beans.User;
import com.itheima.dao.UserDao;

import java.util.List;

public class UserService {
    UserDao userDao = new UserDao();

    public List<User> queryAll() {
        List<User> list = userDao.queryAll();
        return list;
    }

    public boolean delete(int id) {
        boolean b = userDao.delete(id);
        return b;
    }

    public boolean updateById(int id,User user) {
        boolean b = userDao.updateById(id,user);
        return b;
    }

    public User queryById(int id) {
        User user = userDao.queryById(id);
        return user;
    }

    public boolean addUser(User user) {
        boolean b = userDao.addUser(user);
        return b;
    }

    public List<User> queryByName(String name) {

        List<User> list = userDao.queryByName(name);
        return list;
    }

    public PageBean findUserByPage(int currentPage, int pageSize) {
//        private List<User> userList;
//        private int pageSize;
//        private int currentPage;
//        private int nextPage;
//        private int prePage;
//        private int totalPage;
//        private int totalCount;
        int n1 = (currentPage - 1) * pageSize;
        int n2 = pageSize;
        List<User> users = userDao.findUserByPage(n1, n2);
        PageBean pageBean = new PageBean();
        pageBean.setUserList(users);
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setNextPage(currentPage + 1);
        pageBean.setPrePage(currentPage - 1);
        int pages = 0;
        int count = userDao.countAll();
        if (count % pageSize != 0) {
            pages = count / pageSize + 1;
        }else
            pages = count / pageSize;
        pageBean.setTotalPage(pages);
        pageBean.setTotalCount(count);
        return pageBean;
    }
}
