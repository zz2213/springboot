package com.zz.secondhand.utils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @author Administrator
 * @title: RequestWrapper
 * @projectName mybatistest
 * @description: 拦截器读取body数据 解决只能读取流一次的问题
 * @date 2019/4/269:25
 */
public class RequestWrapper extends HttpServletRequestWrapper {
    private final String body;
    public RequestWrapper(HttpServletRequest request) {
        super(request);
        body=request.getParameter("token");
    }
    public String getBody() {
        return this.body;
    }
}
