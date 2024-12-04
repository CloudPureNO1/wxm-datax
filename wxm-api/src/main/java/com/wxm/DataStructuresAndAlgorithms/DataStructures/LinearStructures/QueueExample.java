package com.wxm.DataStructuresAndAlgorithms.DataStructures.LinearStructures;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 在Java中，Queue是一个抽象类，不能被实例化。但是，你可以使用LinkedList类来创建一个队列接口的实现。
 * LinkedList类实现了Queue接口，因此你可以使用LinkedList来代替Queue。
 *
 * 在这个示例中，我们使用LinkedList创建了一个Queue<Integer>，
 * 并使用offer方法向队列中添加元素，使用poll方法从队列中取出元素。
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 13:10:12
 */
public class QueueExample {
    public static void main(String[] args) {
        // 使用LinkedList创建一个队列
        Queue<Integer> queue = new LinkedList<>();

        // 入队
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        // 出队
        System.out.println(queue.poll()); // 输出：1
        System.out.println(queue.poll()); // 输出：2
        System.out.println(queue.poll()); // 输出：3
    }
}
