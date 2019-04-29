package com.zz.secondhand.utils;

import java.io.*;

/**
 * @author Administrator
 * @title: SerialUtil
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/1914:35
 */
public class SerialUtil {
    public static void saveObject(String path, Object saveObject){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        File f = new File(path);
        try{
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(saveObject);
        } catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(oos != null){
                    oos.close();
                }
                if(fos != null){
                    fos.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static Object restoreObject(String path){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Object object = null;
        File f = new File(path);
        if(!f.exists()){
            return null;
        }

        try{
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            object = ois.readObject();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        } finally{
            try{
                if(ois != null) {ois.close();}
                if(fis != null) {fis.close();}
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return object;
    }
}
