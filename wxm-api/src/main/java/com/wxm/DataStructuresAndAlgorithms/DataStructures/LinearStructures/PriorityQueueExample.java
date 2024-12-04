package com.wxm.DataStructuresAndAlgorithms.DataStructures.LinearStructures;

import java.util.PriorityQueue;

/**
 * 在Java中，实现优先级队列（Priority Queue）最简单的方式是使用java.util.PriorityQueue类。PriorityQueue是一个基于优先堆（一种特殊的二叉堆）的无界优先级队列。元素的出队顺序并非按照插入顺序，而是按照它们的自然排序顺序，或者由Comparator提供的顺序。
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 13:14:19
 */
public class PriorityQueueExample {
    public static void main(String[] args) {
        // 创建一个默认的优先级队列
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // 添加元素到优先级队列
        priorityQueue.add(10);
        priorityQueue.add(1);
        priorityQueue.add(5);
        priorityQueue.add(3);
        priorityQueue.add(15);

        System.out.println("Initial Priority Queue: " + priorityQueue);

        // 获取并移除优先级队列中的最小元素
        Integer minElement = priorityQueue.poll();
        System.out.println("Removed the minimum element from Priority Queue: " + minElement);
        System.out.println("Priority Queue After removing an element: " + priorityQueue);

        // 查看但不移除优先级队列中的最小元素
        Integer peekMinElement = priorityQueue.peek();
        System.out.println("Peek at the minimum element in Priority Queue: " + peekMinElement);
    }
}
