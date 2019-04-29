package com.zz.secondhand.Interceptor;

import com.alibaba.fastjson.JSON;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.entity.User;
import com.zz.secondhand.utils.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @title: TokenInterceptor
 * @projectName mybatistest
 * @description: TODO
 * @date 2019/4/269:05
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        Token token= JSON.parseObject(body,Token.class);
        if(StringUtils.isEmpty(body)){
            PrintWriter writer=response.getWriter();
            Map<String,Object> map=new HashMap<String,Object>();
            writer.print("token为空");
            return false;
        }else
        {
            String tokenresult=stringRedisTemplate.opsForValue().get(token.getUserId().toString());
            if (token.getTokenData().equals(tokenresult))
            {
                return true;
            }
            else {
                PrintWriter writer=response.getWriter();
                writer.print("token错误");
                return false;
            }

        }



    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
