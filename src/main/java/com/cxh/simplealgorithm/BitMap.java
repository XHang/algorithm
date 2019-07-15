package com.cxh.simplealgorithm;

import java.util.Arrays;
import java.util.BitSet;

/**
 * 简单的BitMap算法，用于快速查找数据
 */
public class BitMap {
    //一个long有64个二进制位，因为是8个字节，一个字段有8个二进制位
    long[] worlds = new long[1];
    int worldSize = Long.SIZE;

    public BitMap(int[] arr) {
        for (int i : arr) {
            System.out.printf("当前要存的数值是%d \n",i);
            //计算要安排到Long数组的第几个里面，比如要记录的值是65，就要记录到第二个long数组里面
            int longIndex = getStorageIndex(i);
            System.out.printf("当前要安排到long数组的第%d个元素 \n",longIndex);
            int bitIndex =  getStorageBit(i);
            System.out.printf("当前要存储到long的第%d位上 \n",bitIndex);
            System.out.printf("当前的值%d要存到数组里第%d个元素的第%d位上 \n",i,longIndex,bitIndex);
            //扩容BitMap里面的worlds
            expansion(longIndex);
            //说是第3个元素,其实是数组里面的角标为2的元素
            long container = this.worlds[longIndex-1];
            System.out.println("原先long容器为");
            System.out.println(Long.toBinaryString(container));
            long part = Long.MAX_VALUE+1>>>bitIndex-1;
            System.out.println("需要合并的数是");
            System.out.println(Long.toBinaryString(part));
            container = container | part;
            System.out.println("合并的结果是");
            System.out.println(Long.toBinaryString(container));
            //设置回去
            this.worlds[longIndex-1] = container;
        }
    }

    /**
     * 得到这个数组应该存在long的第几位元素上，从左到右数起
     * @param i
     * @return
     */
    private int getStorageBit(int i) {
        return i%worldSize;
    }

    /**
     * 得到这个数字应该存在第几个Long数组里面
     * @param num
     * @return
     */
    private int getStorageIndex(int num){
        return   (num/worldSize)+1;
    }

    /**
     * 使内部的long数组达到指定长度
     * @param count
     */
    private void expansion(int count) {
        int worldsSize = this.worlds.length;
        if (worldsSize>=count){
            return;
        }
        System.out.printf("当前bitSet里面的long数组大小为%d,小于目标数%d，需要扩容 \n",worldsSize,count);
        long[] newWorlds = new long[count];
        moveToNewWorlds(newWorlds);
        this.worlds = newWorlds;
    }

    private void moveToNewWorlds(long[] newWorlds) {
        for (int i = this.worlds.length - 1; i >= 0; i--) {
            long data = this.worlds[i];
            newWorlds[i] = data;
        }
    }
    public boolean isExist(int targer){
        System.out.printf("当前要判断是否存在的数字是%d \n",targer);
        int size = this.worlds.length;
        int targetIndex = getStorageIndex(targer);
        System.out.printf("假设这个值存在，那么它应该存在long数组的第%d位 \n",targetIndex);
        //然而实际上，bitMap并没有存到这个量级，所以不存在该值
        if (size<targetIndex){
            System.out.printf("bitMap最大只存到第%d位，故不存在 \n",size);
            return false;
        }
        //bitMap存到这个量级了，接下来看它应该在二进制的第几位
        long container = this.worlds[targetIndex-1];
        System.out.println("要查询的目标long是");
        System.out.println(Long.toBinaryString(container));
        int bitIndex = getStorageBit(targer);
        System.out.printf("该值应该在第%d个long数组里，从左到右数起第%d位\n",targetIndex,bitIndex);
        System.out.printf("将目标long里面，从左到右数起第%d位，移动到从右数起的第1位，结果是 \n",bitIndex);
        //由于存的时候是从左到右存的，bitIndex就是从左到右数起的，那从右到左数起呢，就是64-bitIndex
        long moveResult = container>>>(64-bitIndex);
        System.out.println(Long.toBinaryString(moveResult));
        //&1的作用在于把原先的矩阵除了第一位，所有位数都置0，也就是提取第一位元素
        long flag =(moveResult)&1;
        System.out.println("&1结果是");
        System.out.println(Long.toBinaryString(flag));
        return flag==1;
    }

}
