package com.codecly.seckill.exception;

/**
 * 秒杀关闭异常（运行期异常）
 *
 * @author maxinchun
 * @create 2016-05-25 22:03
 */
public class SeckillCloseException extends SeckillException{

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
