package com.codecly.seckill.service;

import com.codecly.seckill.dto.Exposer;
import com.codecly.seckill.dto.SeckillExecution;
import com.codecly.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by maxinchun on 2016/6/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
        // list=[Seckill{seckillId=1000, name='1000元秒杀iphone6', number=100, startTime=Fri Jun 03 23:10:34 CST 2016, endTime=Sun Jun 05 00:00:00 CST 2016, createTime=Mon May 23 20:21:18 CST 2016}, Seckill{seckillId=1001, name='500元秒杀ipad2', number=200, startTime=Wed Jun 01 00:00:00 CST 2016, endTime=Sun Jun 05 00:00:00 CST 2016, createTime=Mon May 23 20:21:18 CST 2016}, Seckill{seckillId=1002, name='300元秒杀小米4', number=300, startTime=Wed Jun 01 00:00:00 CST 2016, endTime=Sun Jun 05 00:00:00 CST 2016, createTime=Mon May 23 20:21:18 CST 2016}, Seckill{seckillId=1003, name='200元秒杀红米note', number=400, startTime=Wed Jun 01 00:00:00 CST 2016, endTime=Sun Jun 05 00:00:00 CST 2016, createTime=Mon May 23 20:21:18 CST 2016}]
    }

    @Test
    public void testGetById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
        // seckill=Seckill{seckillId=1000, name='1000元秒杀iphone6', number=100, startTime=Fri Jun 03 23:10:34 CST 2016, endTime=Sun Jun 05 00:00:00 CST 2016, createTime=Mon May 23 20:21:18 CST 2016}
    }

    @Test
    public void testExportSeckillUrl() throws Exception {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exproser={}", exposer);
        // exproser=Exposer{exposed=true, md5='c0e0a6155042b1eb6a617bd7838c3ee4', seckillId=1000, now=0, start=0, end=0}
    }

    @Test
    public void testExecuteSeckill() throws Exception {
        long id = 1000;
        long phone = 13502198731L;
        String md5 = "c0e0a6155042b1eb6a617bd7838c3ee4";
        SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
        logger.info("result={}", seckillExecution);
        /*
        23:42:08.659 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
        23:42:08.664 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@14fa86ae]
        23:42:08.668 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@2a225dd7] will be managed by Spring
        23:42:08.671 [main] DEBUG c.c.s.dao.SeckillDao.reduceNumber - ==>  Preparing: update seckill set number = number -1 where seckill_id = ? and start_time <= ? and end_time >= ? and number >= 0;
        23:42:08.694 [main] DEBUG c.c.s.dao.SeckillDao.reduceNumber - ==> Parameters: 1000(Long), 2016-06-03 23:42:08.653(Timestamp), 2016-06-03 23:42:08.653(Timestamp)
        23:42:08.703 [main] DEBUG c.c.s.dao.SeckillDao.reduceNumber - <==    Updates: 1
        23:42:08.703 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@14fa86ae]
        23:42:08.704 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@14fa86ae] from current transaction
        23:42:08.704 [main] DEBUG c.c.s.d.S.insertSuccessKilled - ==>  Preparing: insert ignore into success_killed(seckill_id, user_phone, state) values (?, ?, 0)
        23:42:08.705 [main] DEBUG c.c.s.d.S.insertSuccessKilled - ==> Parameters: 1000(Long), 13502198731(Long)
        23:42:08.721 [main] DEBUG c.c.s.d.S.insertSuccessKilled - <==    Updates: 1
        23:42:08.725 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@14fa86ae]
        23:42:08.726 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@14fa86ae] from current transaction
        23:42:08.727 [main] DEBUG c.c.s.d.S.queryByIdWithSeckill - ==>  Preparing: select sk.seckill_id, sk.user_phone, sk.create_time, sk.state, s.seckill_id "seckill.seckill_id", s.name "seckill.name", s.number "seckill.number", s.start_time "seckill.start_time", s.end_time "seckill.end_time", s.create_time "seckill.create_time" from success_killed sk inner join seckill s on sk.seckill_id = s.seckill_id where sk.seckill_id = ? and sk.user_phone = ?
        23:42:08.727 [main] DEBUG c.c.s.d.S.queryByIdWithSeckill - ==> Parameters: 1000(Long), 13502198731(Long)
        23:42:08.739 [main] DEBUG c.c.s.d.S.queryByIdWithSeckill - <==      Total: 1
        23:42:08.743 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@14fa86ae]
        23:42:08.743 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@14fa86ae]
        23:42:08.744 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@14fa86ae]
        23:42:08.744 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@14fa86ae]
        23:42:08.819 [main] INFO  c.c.s.service.SeckillServiceTest - result=SeckillExecution{seckillId=1000, state=1, stateInfo='秒杀成功', successKilled=SuccessKilled{seckillId=1000, userPhone=13502198731, state=0, createTime=Fri Jun 03 23:42:08 CST 2016, seckill=Seckill{seckillId=1000, name='1000元秒杀iphone6', number=98, startTime=Fri Jun 03 23:42:08 CST 2016, endTime=Sun Jun 05 00:00:00 CST 2016, createTime=Mon May 23 20:21:18 CST 2016}}}
         */
    }
}