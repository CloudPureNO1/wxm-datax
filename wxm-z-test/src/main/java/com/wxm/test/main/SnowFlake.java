package com.wxm.test.main;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-04-06 10:13:37
 */
// The code below is an implementation of the Snowflake algorithm in Java
// It generates unique IDs based on the current timestamp, worker ID, and sequence number

public class SnowFlake {
    private long workerId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;
    private static final long twepoch = 1288834974657L;
    private static final long workerIdBits = 5L;
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private static final long sequenceBits = 12L;
    private static final long workerIdShift = sequenceBits;
    private static final long timestampLeftShift = sequenceBits + workerIdBits;
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    public SnowFlake(long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("Worker ID can't be greater than %d or less than 0", maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }







    //==============================Test=============================================
    /** 测试 */
    public static void main(String[] args) {
        SnowFlake idWorker = new SnowFlake(1);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            System.out.println(Long.toBinaryString(id));
            System.out.println(id);
            System.out.println(Long.toBinaryString(id).length());


        }
    }
}

// The Snowflake algorithm is a unique ID generator that is commonly used in distributed systems.
// It generates IDs based on the current timestamp, worker ID, and sequence number.
// This ensures that each ID is unique and can be used to identify a specific object or event.