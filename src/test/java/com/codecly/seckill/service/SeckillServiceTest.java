package com.codecly.seckill.service;

import com.codecly.seckill.dto.Exposer;
import com.codecly.seckill.dto.SeckillExecution;
import com.codecly.seckill.entity.Seckill;
import com.codecly.seckill.exception.RepeatKillException;
import com.codecly.seckill.exception.SeckillCloseException;
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

    // 集成测试代码完整逻辑，注意可重复执行
    @Test
    public void testSeckillLogic() throws Exception {
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long phone = 13502198721L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
                logger.info("result={}", seckillExecution);
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            }
        } else {
            // 秒杀未开启
            logger.warn("exposer={}", exposer);
        }
    }

}