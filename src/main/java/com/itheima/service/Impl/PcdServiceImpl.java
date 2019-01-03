package com.itheima.service.Impl;

import com.alibaba.fastjson.JSON;
import com.itheima.beans.Pcd;
import com.itheima.dao.PcdDao;
import com.itheima.service.PcdService;
import com.itheima.utils.JedisUtil;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

public class PcdServiceImpl implements PcdService {
    private PcdDao pcdDao = new PcdDao();

    @Override
    public String findPcdByPid(int pid) {
        //1.优先从redis缓存读取数据
        long time1 = System.currentTimeMillis();
        Jedis jedis = JedisUtil.getJedis();
        String json = jedis.get("pcd" + pid);
        long time2 = System.currentTimeMillis();
        if (StringUtils.isEmpty(json)) {
            //2.如果缓存中没有获取到数据，那么调用dao从数据库获取
            long time3 = System.currentTimeMillis();
            List<Pcd> pcdList = pcdDao.findPcdByPid(pid);
            json = JSON.toJSONString(pcdList);
            jedis.set("pcd" + pid, json);
            long time4 = System.currentTimeMillis();
            System.out.println("从数据库获取数据,耗时：" + (time4 - time3));
        }else {
            System.out.println("从redis缓存读取数据,耗时：" + (time2 - time1));
        }
        JedisUtil.close(jedis);
        return json;
    }

    @Override
    public String findAllProvince() {

        return null;
    }
    /*@Override
    public String findPcdByPid(int pid) {
        List<Pcd> list = pcdDao.findPcdByPid(pid);
        String s = JSON.toJSONString(list);
        return s;
    }

    @Override
    public String findAllProvince() {
        List<Pcd> list = pcdDao.findAllProvince();
        String s = JSON.toJSONString(list);
        return s;
    }*/
}
