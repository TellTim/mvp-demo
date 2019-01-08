package com.hunter.mvp.app.exception;

/**
 * @program: MVXdemo
 * @packageName: com.hunter.mvp.app.action
 * @fileName: Test
 * @author: Tell.Tim
 * @date: 2019/1/7 10:14
 * @description: 自定义异常类
 */
public class BaseException extends Exception {

    /**
     * 错误码
     */

    private int code;

    /**
     * 构造异常
     *
     * @param code 异常状态码
     * @param msg 异常讯息
     */
    public BaseException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    /**
     * 构造异常
     *
     * @param code 异常状态码
     * @param ex 异常来源
     */
    public BaseException(int code, Exception ex) {
        super(ex);
        this.code = code;
    }

    /**
     * @return 异常状态码。
     */
    public int getErrorCode() {
        return code;
    }
}
