package com.cxh.simplealgorithm;

import org.junit.Assert;
import org.junit.Test;

import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 * 二分法查找测试
 */
public class SearchUtilTest {

    @Test
    public void binarySearchUtil(){
        int[] arr = new int[]{1,3,6,11,66,88,99,123,666,876,999};
        int target = 123;
        int index = SearchUtil.BinarySearch(arr,target);
        Assert.assertTrue(index ==7);
    }

    /**
     * 演示整数溢出的一个小问题
     * 整形溢出不会抛异常，这才是最可怕的
     */
    @Test
    public void integerOverflowTest(){
        //如下，我们将下面的负数进行取绝对值
        int number = -2147483648;
        int abs = Math.abs(number);
        //但是，输出结果是酱紫的，
        Assert.assertTrue(abs == number);
        System.out.println(1.0/0/0 == Infinity);



    }

}
