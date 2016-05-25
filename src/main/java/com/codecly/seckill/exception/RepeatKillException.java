package com.codecly.seckill.exception;

/**
 * 重复秒杀异常（运行期异常）
 *
 * @author maxinchun
 * @create 2016-05-25 22:00
 */
public class RepeatKillException extends SeckillException{

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
