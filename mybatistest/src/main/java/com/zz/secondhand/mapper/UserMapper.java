package com.zz.secondhand.mapper;

import com.zz.secondhand.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/14 16:05
 * @Description:
 */
public interface UserMapper  {
    String Sel(@Param("name") String name);
    User findUserByName(@Param("name") String name);
    User findUserById(@Param("id") Integer id);
    int Register(User user);
    int Update(User user);
    List<User> queryUser(@Param("page") int page, @Param("limit") int limit);
    int queryAllCount();
    int deleteUser(Integer id);
}
