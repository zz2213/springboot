package com.zz.secondhand.mapper;

import com.zz.secondhand.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/14 16:05
 * @Description:
 */
public interface UserMapper {
    public String Sel(@Param("id") String id);
    public int Register(User user);
}
