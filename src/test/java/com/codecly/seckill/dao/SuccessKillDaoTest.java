package com.codecly.seckill.dao;

import com.codecly.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * Created by maxinchun on 2016/5/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKillDaoTest {

    @Resource
    private SuccessKillDao successKillDao;

    @Test
    public void testInsertSuccessKilled() throws Exception {
        /*
        第一次：insertCount=1
        第二次：insertCount=0
         */
        long id = 1001L;
        long phone = 15212359645L;
        int insertCount = successKillDao.insertSuccessKilled(id, phone);
        System.out.println("insertCount=" + insertCount);
    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception {
        /*
        SuccessKilled{seckillId=1000, userPhone=15212359645, state=-1, createTime=Tue May 24 23:50:53 CST 2016, seckill=Seckill{seckillId=1000, name='1000元秒杀iphone6', number=100, startTime=Sun Nov 01 00:00:00 CST 2015, endTime=Mon Nov 02 00:00:00 CST 2015, createTime=Mon May 23 20:21:18 CST 2016}}
        Seckill{seckillId=1000, name='1000元秒杀iphone6', number=100, startTime=Sun Nov 01 00:00:00 CST 2015, endTime=Mon Nov 02 00:00:00 CST 2015, createTime=Mon May 23 20:21:18 CST 2016}

        SuccessKilled{seckillId=1001, userPhone=15212359645, state=0, createTime=Wed May 25 00:01:53 CST 2016, seckill=Seckill{seckillId=1001, name='500元秒杀ipad2', number=200, startTime=Sun Nov 01 00:00:00 CST 2015, endTime=Mon Nov 02 00:00:00 CST 2015, createTime=Mon May 23 20:21:18 CST 2016}}
        Seckill{seckillId=1001, name='500元秒杀ipad2', number=200, startTime=Sun Nov 01 00:00:00 CST 2015, endTime=Mon Nov 02 00:00:00 CST 2015, createTime=Mon May 23 20:21:18 CST 2016}

         */
        long id = 1001L;
        long phone = 15212359645L;
        SuccessKilled successKilled = successKillDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}