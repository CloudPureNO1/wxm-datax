package com.wxm.DataStructuresAndAlgorithms.DataStructures.LinearStructures;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 需要注意的是，Stack 类的使用在现代 Java 编程中并不推荐，因为它的一些方法没有抛出异常而是返回特殊值，这可能导致程序中难以发现的错误。例如，pop() 和 peek() 方法在栈为空时不会抛出异常，而是返回特殊值 null 或 -1。这与现代集合框架的设计理念不符，后者倾向于在非法操作时抛出异常，比如 NoSuchElementException。
 *
 * 对于新的代码编写，更推荐使用 Deque（双端队列）接口的实现类，如 ArrayDeque 或 LinkedList，这些类提供了更好的性能和更安全的方法来模拟栈的行为。
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 13:11:31
 */
public class DequeExample {
    public static void main(String[] args) {
        Deque<String> stack = new ArrayDeque<>();

        stack.push("one");
        stack.push("two");
        stack.push("three");

        System.out.println("Stack: " + stack);

        String topElement = stack.peek();
        System.out.println("Top element is: " + topElement);

        String poppedElement = stack.pop();
        System.out.println("Popped element: " + poppedElement);
        System.out.println("Stack after pop: " + stack);
    }
}
