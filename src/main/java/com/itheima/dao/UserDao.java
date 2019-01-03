package com.itheima.dao;

import com.itheima.beans.User;
import com.itheima.utils.JDBCTemplateUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDao {
    JdbcTemplate jdbcTemplate = JDBCTemplateUtils.getJdbcTemplate();

    public List<User> queryAll() {
        String sql = "select * from t_user";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));

        return users;
    }

    public boolean delete(int id) {
        String sql = "delete from t_user where id=?";
        int update = jdbcTemplate.update(sql, id);

        return update > 0;
    }

    public User queryById(int id) {
        String sql = "select * from t_user where id=?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        return user;
    }

    public boolean updateById(int id,User user) {
        String sql = "update t_user set name=?,sex=?,age=?,address=?,qq=?,email=? where id=?";
        int update = jdbcTemplate.update(sql, user.getName(), user.getSex(), user.getAge(), user.getAddress(),
                user.getQq(), user.getEmail(), id);
        return update > 0;
    }

    public boolean addUser(User user) {
        String sql = "insert into t_user values(null,?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, user.getName(), user.getSex(), user.getAge(),
                user.getAddress(), user.getQq(), user.getEmail());
        return update > 0;
    }

    public List<User> queryByName(String name) {
        String sql = "select * from t_user where name=?";
        List<User> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), name);
        return query;
    }

    public List<User> findUserByPage(int n1, int n2) {
        String sql = "select * from t_user limit ?,?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), n1, n2);
        return users;
    }

    public int countAll() {
        String sql = "select count(*) from t_user";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        return integer;
    }
}
