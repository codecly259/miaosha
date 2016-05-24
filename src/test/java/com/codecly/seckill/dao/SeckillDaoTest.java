package com.codecly.seckill.dao;

import com.codecly.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *  配置spring和junit整合，junit启动时加载springIOC容器
 *  spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    // 注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testReduceNumber() throws Exception {
//        23:13:51.530 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@792b749c] will not be managed by Spring
//        23:13:51.542 [main] DEBUG c.c.s.dao.SeckillDao.reduceNumber - ==>  Preparing: update seckill set number = number -1 where seckill_id = ? and start_time <= ? and end_time >= ? and number >= 0;
//        23:13:51.569 [main] DEBUG c.c.s.dao.SeckillDao.reduceNumber - ==> Parameters: 1000(Long), 2016-05-24 23:13:51.278(Timestamp), 2016-05-24 23:13:51.278(Timestamp)
//        23:13:51.615 [main] DEBUG c.c.s.dao.SeckillDao.reduceNumber - <==    Updates: 0
//        23:13:51.615 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@742ff096]
//        updateCount=0
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L,killTime);
        System.out.println("updateCount=" + updateCount);
    }

    @Test
    public void testQueryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll() throws Exception {
        /*
         * org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.binding.BindingException:
         * Parameter 'offset' not found. Available parameters are [0, 1, param1, param2]
         */
        // List<Seckill> queryAll(int offset, int limit);
        // java没有保存形参的记录：queryAll(int offset, int limit) -> queryAll(arg0, arg1)
        List<Seckill> seckillList = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckillList) {
            System.out.println(seckill);
        }
    }
}
