package com.wxm.pattern.templateMethod.real;
import java.util.List;
/**
 * 抽象类，定义批处理作业的骨架
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 15:25:14
 */
abstract class BatchJob {
    public final void execute() {
        List<String> data = readData();
        List<String> transformedData = transform(data);
        writeData(transformedData);
    }

    // 抽象方法，由子类实现
    protected abstract List<String> readData();
    protected abstract List<String> transform(List<String> data);
    protected abstract void writeData(List<String> data);

    // 可选的钩子方法，允许子类在执行前后做额外的操作
    protected void beforeExecution() {}
    protected void afterExecution() {}

    // 执行前后的钩子方法调用
    public void run() {
        beforeExecution();
        execute();
        afterExecution();
    }
}