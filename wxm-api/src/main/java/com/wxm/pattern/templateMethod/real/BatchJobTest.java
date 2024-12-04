package com.wxm.pattern.templateMethod.real;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 15:27:04
 */
public class BatchJobTest {
    public static void main(String[] args) {
        BatchJob job = new DatabaseBatchJob();
        job.run();
    }
}
