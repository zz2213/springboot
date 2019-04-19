package com.zz.secondhand.entity;

import com.zz.secondhand.utils.SerialUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class User implements Serializable,Cloneable{

    private static final long serialVersionUID = 1L;

    public static void setInstance(User instance) {
        SerialUtil.saveObject("./obj.out",instance);
        User.instance = instance;
    }

    private  static User instance;

    private Integer id;

    private String name;

    private String realname;

    private String password;

    private Integer number;

    private String school;

    private byte[] image;

    private String qq;

    public User() { }

    public static User getInstance(){
        if(instance != null){
            User user =(User) SerialUtil.restoreObject("/mnt/sdcard/obj.out");
            if(user==null){
                SerialUtil.saveObject("/mnt/sdcard/obj.out",instance);
            }
        }else{
            instance =(User) SerialUtil.restoreObject("/mnt/sdcard/obj.out");

        }
        return instance;
    }


    public Integer getId() {
        return id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public User readReaolve() throws ObjectStreamException,CloneNotSupportedException{
        instance = (User) this.clone();
        return instance;
    }

    public void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
    }

    public Object Clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public void clear(){
       instance.setId(null);
        instance.setSchool(null);
        instance.setQq(null);
        instance.setNumber(null);
        instance.setPassword(null);
        instance.setRealname(null);
        instance.setImage(null);
        instance.setName(null);
        save();
    }

    public void save(){
        SerialUtil.saveObject("/mnt/sdcard/obj.out",instance);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", realname='" + realname + '\'' +
                ", password='" + password + '\'' +
                ", number=" + number +
                ", school='" + school + '\'' +
                ", image='" + image + '\'' +
                ", qq='" + qq + '\'' +
                '}';
    }
}
