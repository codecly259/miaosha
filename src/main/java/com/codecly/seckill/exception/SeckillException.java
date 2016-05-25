package com.codecly.seckill.exception;

/**
 * 秒杀相关业务异常
 *
 * @author maxinchun
 * @create 2016-05-25 22:05
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
