package com.chen.study.concurrent.concurrent3.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 陈添明
 * @date 2018/11/4
 */
public class AtomicReferenceTest {


    public static void main(String[] args) {
        AtomicReference<Simple> atomicReference = new AtomicReference<>(new Simple("chen", 12));

        System.out.println(atomicReference.get());
        atomicReference.set(new Simple("wang", 34));
        System.out.println(atomicReference.get());
        boolean b = atomicReference.compareAndSet(new Simple("wang", 34), new Simple("liu", 23));
        System.out.println(b);
        System.out.println(atomicReference.get());

        // 在lambda表达式式中替换引用   在非多线程下的适用场景
        AtomicReference<Simple> simple1 = new AtomicReference<>(new Simple("yy", 23));
        List<String> list = new ArrayList();
        list.forEach(s -> {
            if (Objects.equals("yy", s)){
                simple1.set(new Simple("pp", 35));
            }
        });

    }





    static class Simple {
        private String name;
        private Integer age;

        public Simple(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Simple{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
