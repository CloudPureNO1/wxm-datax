package com.wxm.pattern.strategy;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 13:23:02
 */
public class StrategyTest {
    public static void main(String[] args) {
        int[] numbers = {3, 5, 1, 2, 4};

        SorterContext context = new SorterContext(new BubbleSortStrategy());
        context.sort(numbers);
        printArray(numbers);

        context = new SorterContext(new InsertionSortStrategy());
        context.sort(numbers);
        printArray(numbers);

        context = new SorterContext(new SelectionSortStrategy());
        context.sort(numbers);
        printArray(numbers);
    }

    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
