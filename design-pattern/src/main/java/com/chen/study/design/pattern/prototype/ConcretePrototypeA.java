package com.chen.study.design.pattern.prototype;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.List;

/**
 * 具体对象A
 *
 * @author 陈添明
 * @date 2019/3/30
 */
@Getter
@Setter
public class ConcretePrototypeA implements Cloneable, Serializable {

    private int age;
    private String name;
    private List<String> hobbies;

    /**
     * 通过clone()方法来实现浅度拷贝
     *
     * @return
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        // object的clone()方法为native方法，效率更高。
        // 不要自己new对象再赋值
        return super.clone();
    }

    /**
     * 通过对象序列化实现深拷贝（推荐）
     * 序列化方式可以是JDK序列化，也可以是JSON序列化
     * @return
     */
    public Object deepClone() {
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(this);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return  ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("深拷贝失败！", e);
        }
    }

    /**
     * 通过对象序列化实现深拷贝（推荐）
     * 序列化方式可以是JDK序列化，也可以是JSON序列化, 推荐JSON序列化
     * @return
     */
    public ConcretePrototypeA deepCloneWithJSON() {
        String jsonString = JSON.toJSONString(this);
        return JSON.parseObject(jsonString, this.getClass());
    }
}
