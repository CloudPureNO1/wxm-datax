package com.wxm.DataStructuresAndAlgorithms.DataStructures.LinearStructures;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 13:15:36
 */
public class PriorityQueueWithTasks {
    public static void main(String[] args) {
        PriorityQueue<Task> taskQueue = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getPriority() - t2.getPriority();
            }
        });

        taskQueue.add(new Task("Task A", 3));
        taskQueue.add(new Task("Task B", 1));
        taskQueue.add(new Task("Task C", 2));

        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll());
        }
    }
}
