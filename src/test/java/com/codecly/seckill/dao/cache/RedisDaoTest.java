package com.codecly.seckill.dao.cache;

import com.codecly.seckill.dao.SeckillDao;
import com.codecly.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by maxinchun on 2016/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private long id = 1001;
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testGetSeckill() throws Exception {
        Seckill seckill = redisDao.getSeckill(id);
        if (seckill == null) {
            seckill = seckillDao.queryById(id);
            if (seckill != null) {
                String result = redisDao.putSeckill(seckill);
                logger.info(result);
                seckill = redisDao.getSeckill(id);
                System.out.println("seckill" + seckill);
            }
        } else {
            logger.info("seckill:" + seckill);
        }
    }

    @Test
    public void testPutSeckill() throws Exception {

    }
}