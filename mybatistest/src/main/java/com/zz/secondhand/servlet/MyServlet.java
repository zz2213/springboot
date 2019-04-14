package com.zz.secondhand.servlet;

import com.zz.secondhand.entity.User;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/14 16:16
 * @Description:
 */
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream in=req.getInputStream();
        User user = null;
        ObjectInputStream obj=new ObjectInputStream(in);
        try {
            user = (User)obj.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            obj.close();
        }
        resp.setContentType("application/x-java-serialized-object");
      System.out.println(user.getName());
        OutputStream out=resp.getOutputStream();
        ObjectOutputStream outObj=new ObjectOutputStream(out);
        user.setPassword("9964646");
              outObj.writeObject(user);
               outObj.flush();
               outObj.close();

//        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
