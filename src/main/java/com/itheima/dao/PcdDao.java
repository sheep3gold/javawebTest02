package com.itheima.dao;

import com.itheima.beans.Pcd;
import com.itheima.utils.JDBCTemplateUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PcdDao {

    public List<Pcd> findPcdByPid(int pid) {
        String sql = "select * from pcd where pid=? ";
        JdbcTemplate jdbcTemplate = JDBCTemplateUtils.getJdbcTemplate();
        List<Pcd> l = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pcd.class), pid);
        return l;
    }

    public List<Pcd> findAllProvince() {
        String sql = "select * from pcd where pid=0";
        JdbcTemplate jdbcTemplate = JDBCTemplateUtils.getJdbcTemplate();
        List<Pcd> l = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pcd.class));
//        String string = JSON.toJSONString(l);
        return l;
    }
}
