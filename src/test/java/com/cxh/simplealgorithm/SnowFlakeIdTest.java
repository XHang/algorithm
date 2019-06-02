package com.cxh.simplealgorithm;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SnowFlakeIdTest {

    @Test
    public void nextId() {
        ArrayList<Long> list = new ArrayList<>();
        SnowFlakeId gen = new SnowFlakeId(31, 31);
        for (int i = 0; i < 1000; i++) {
            Long id = gen.nextId();
            if (id < 0) {
                throw new RuntimeException("生成失败，生成的id["+id+"]是负数");
            }
            if (list.contains(id)){
                throw new RuntimeException("生成失败，生成了重复ID");
            }
            list.add(id);
            System.out.println("id binary is【" + Long.toBinaryString(id) + "】");
            System.out.println("id is 【" + id + "】");
        }
    }

    @Test
    public void test(){
        System.out.println(Long.toBinaryString(-1L));
    }


}