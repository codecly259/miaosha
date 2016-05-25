package com.codecly.seckill.service.impl;

import com.codecly.seckill.dao.SeckillDao;
import com.codecly.seckill.dto.Exposer;
import com.codecly.seckill.dto.SeckillExecution;
import com.codecly.seckill.entity.Seckill;
import com.codecly.seckill.entity.SuccessKilled;
import com.codecly.seckill.exception.SeckillException;
import com.codecly.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * DESCRIPTION
 *
 * @author maxinchun
 * @create 2016-05-25 22:17
 */
public class SeckillServiceImpl implements SeckillService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private SeckillDao seckillDao;
    private SuccessKilled successKilled;

    // md5盐值字符串，用于混淆 MD5
    public final String salt = "akfakdfjkfaj;2u4891ufakj `0!#&*$jladfj57329";

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if(seckill == null){
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if(nowTime.getTime() < startTime.getTime()
            || nowTime.getTime() > endTime.getTime()){
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        // 转化特定字符串的过程，不可逆
        String md5 = getMd5(seckillId); //TODO
        return new Exposer(true, md5, seckillId);
    }

    private String getMd5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    public SeckillExecution excuteSeckill(long seckillId, long userPhone, String md5) throws SeckillException {
        return null;
    }
}
