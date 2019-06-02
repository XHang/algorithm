package com.cxh.simplealgorithm;

/**
 * 这是一个分布式集群生成全局唯一且递增ID生成器
 * 它有以下部分组成，从右向左看
 * 前12位是序列号，用于在毫秒内生成多个序列号
 * 前13-18是工作机器ID,总共5位
 * 前19-24是数据中心ID,总共5位
 * 24-60是时间戳，总共36位
 */
public class SnowFlakeId {
    /**
     * 工作机器ID
     */
    private long workId;
    /**
     * 数据中心ID
     */
    private long centerId;
    /**
     * 开始时间  第一次用来记录ID的时间：2019-06-02 10:32:53
     */
    private long startTime = 1559442773000L;
    /**
     * 上一个批次的时间戳
     */
    private long lastTime = 1L;

    /**
     * 序列号(这里给一个初始值，而不是由调用者指定，因为即使调用者指定，第一次生成序列号时肯定也会被置为0)
     */
    private long serialNo = 0L;

    /**
     * 序列号所占位数
     */
    private long serialBytes = 12L;

    /**
     * 工作ID占二进制位数
     */
    private long workBytes = 5L;

    /**
     * 数据中心占二进制位数
     */
    private long centerBytes = 5L;
    /**
     * 时间戳占二进制数
     */
    private long timeBytes = 36L;
    /**
     * 工作节点应该向左移动的位数 = 序列号所占位数
     */
    private long workShiftNum = serialBytes;

    /**
     * 数据中心向左移动的位数 = 序列号所占位数+工作节点所占位数
     */
    private long centerShiftNum = serialBytes + workBytes;

    /**
     * 时间戳向左移动的位数 =  序列号所占位数+工作节点所占位数+数据中心所占位数;
     */
    private long timeShiftNum = serialBytes + workBytes + centerBytes;
    /**
     * 序列号十进制最大允许多少
     */
    private long maxSerial = -1L ^ (-1L << serialBytes);

    /**
     * 最大工作机ID
     */
    private long maxWorkId = -1l ^ (-1 << workBytes);
    /**
     * 最大数据中心ID
     */
    private long maxCenterId = -1L ^ (-1L << centerBytes);

    public SnowFlakeId(long workId, long centerId) {
        if (workId < 0 || workId > maxWorkId) {
            throw new IllegalArgumentException("workId less than 0 or more than the Maximum");
        }
        if (centerId < 0 || centerId > maxCenterId) {
            throw new IllegalArgumentException("center Id less than 0  or more than the Maximum");
        }
        this.workId = workId;
        this.centerId = centerId;
    }

    //必须加锁，因为里面存在状态位，不能同时被一个以上的线程读到
    public synchronized Long nextId() {
        Long nowTime = genTime();
        //如果当前时间戳小于上次运行的时间戳，则说明时钟可能被回拨过，为了避免生成重复性ID,需要拒绝此次生成ID请求
        if (nowTime < lastTime) {
            throw new IllegalStateException("current time is Outdated，Please adjust cpu clock");
        }
        //如果当前时间戳等于上次运行的时间戳，则说明两次运行的时间间隔在毫秒内，需要用下一个序列号保证ID的唯一性
        if (nowTime == lastTime) {
            serialNo = (serialNo + 1L) & maxSerial;
            //如果序列号已经溢出，cpu进入空转状态直至进入下一个毫秒
            if (serialNo == 0) {
                nowTime = waitNextTime();
            }
            //时间戳和上次的时间戳不一样，可以用时间戳来区分ID了，序列号可以不用了,所以归0
        } else {
            serialNo = 0;
        }
        lastTime = nowTime;
        //ID组成的时间戳部分其实是时间差，因为要充分利用好时间戳的存储空间，不能直接塞一个时间戳进去
        return (nowTime - startTime) << timeShiftNum |
                centerId << centerShiftNum |
                workId << workShiftNum |
                serialNo;
    }

    /**
     * 等待至下一个毫秒
     *
     * @return
     */
    private long waitNextTime() {
        while (true) {
            Long currentTime = genTime();
            if (currentTime > lastTime) {
                return currentTime;
            }
        }
    }

    private Long genTime() {
        return System.currentTimeMillis();
    }


}