package com.zz.secondhand;

import com.zz.secondhand.servlet.MyServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@MapperScan("com.zz.secondhand.mapper")
@SpringBootApplication
@EnableCaching
public class MybatistestApplication {
  @Bean
  public ServletRegistrationBean MyServlet1(){
      return new ServletRegistrationBean(new MyServlet(),"/myserv/registor");
  }
    public static void main(String[] args) {
        SpringApplication.run(MybatistestApplication.class, args);

    }

}
