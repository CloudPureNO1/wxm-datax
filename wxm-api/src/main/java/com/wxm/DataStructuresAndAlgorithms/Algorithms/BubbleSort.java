package com.wxm.DataStructuresAndAlgorithms.Algorithms;

import java.util.Random;

/**
 * 冒泡排序（Bubble Sort）
 * 冒泡排序是一种简单的排序算法。它重复地遍历要排序的数列，
 * 一次比较两个元素，如果它们的顺序错误就把它们交换过来。
 * 遍历数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 *
 * 步骤：
 *
 * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 * 对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数；
 * 针对所有的元素重复以上的步骤，除了最后一个；
 * 重复步骤1~3，直到排序完成。
 *
 *
 * 时间复杂度：
 * 冒泡排序（Bubble Sort）的时间复杂度为O(n^2)的原因在于它的基本工作方式涉及到了两层循环，这两层循环的组合导致了时间复杂度的平方级别增长。
 *
 * 冒泡排序的核心思想是重复地遍历待排序的数列，比较每对相邻的元素，如果他们的顺序错误就把他们交换过来。遍历数列的工作是重复进行的，直到没有再需要交换的元素为止，也就是说该数列已经排序完成。
 *
 * 以下是冒泡排序的主要步骤：
 *
 * 比较相邻的元素。如果第一个比第二个大，就交换它们两个。
 * 对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 * 由于冒泡排序需要对n个元素进行n-1次比较（第一趟），然后n-2次（第二趟），依此类推，直到最后需要1次比较（第n-1趟）。因此，总的比较次数是 (n-1) + (n-2) + ... + 1，这是一个等差数列求和的问题，其求和公式为 n*(n-1)/2。
 *
 * 当n较大时，我们可以忽略低阶项和常数项，因此总的时间复杂度可以简化为O(n^2)。
 *
 * 即使在最好的情况下（数组已经是排序好的），冒泡排序仍然需要遍历整个数组来确认所有元素都已经正确排序，这时的时间复杂度为O(n)，但在平均和最坏情况下，时间复杂度都是O(n^2)。
 *
 * 因此，冒泡排序在处理大数据集时效率较低，但对于小数据集或几乎已排序的数据集来说，它仍然是一个可行的选择。
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 15:27:08
 */
public class BubbleSort {
    public static void main(String [] args){

        int [] arr = buildData(20);
        print(arr);
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++");

        bubbleSort(arr);
        print(arr);

        System.out.println();
        System.out.println("*****************");

        bubbleSortFaster(arr);
        print(arr);
        System.out.println();
    }

    public static void print(int arr[]){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    /**
     *
     * @param arr
     */
    public static void bubbleSort(int arr[]){
        long startTime = System.currentTimeMillis();
        // 遍历所有数组元素
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println(">>>耗时："+(System.currentTimeMillis()-startTime)+"ms");

    }
    /**
     * 改进后的方法，时间提速非常之多
     *
     * 这段代码展示的是冒泡排序的一个优化版本，通常被称为“带标志的冒泡排序”。在标准的冒泡排序中，无论数组是否已经完全排序，算法都会遍历整个数组多次，直到预定的遍历次数完成。然而，在最佳情况下（即数组已经是排序好的），标准冒泡排序仍然会执行完整的遍历，这显然是不必要的。
     *
     * 优化版的冒泡排序引入了一个布尔变量swapped，用于跟踪在某次遍历中是否发生了元素交换。如果在一次完整遍历中没有发生任何交换，这实际上意味着数组已经是排序好了的，因此没有必要再进行更多的遍历。此时，算法可以提前终止，避免了不必要的比较和交换操作，从而提高了性能。
     *
     *
     *
     * do {...} while (swapped); 循环确保至少进行一次遍历，这是必要的，因为我们不能事先知道数组是否已经排序。
     * 在每次遍历开始前，swapped被设置为false，表示这次遍历尚未发现需要交换的元素。
     * 当在遍历过程中发现需要交换的元素时，swapped被设置为true。
     * 如果一次遍历结束后swapped仍然是false，这意味着没有发生任何交换，数组已经排序完成，于是do {...} while (swapped);循环终止。
     *
     * @param arr
     */
    public static void bubbleSortFaster(int arr[]){
        long startTime = System.currentTimeMillis();
        boolean swapped;
        do {
            swapped = false;
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换元素
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
        System.out.println(">>>耗时："+(System.currentTimeMillis()-startTime)+"ms");
    }

    public static int [] buildData(int len){
        int[] randomArray = new int[len];
        Random rand = new Random();

        for (int i = 0; i < randomArray.length; i++) {
            // 生成一个介于-1000和1000之间的随机整数
            randomArray[i] = rand.nextInt(2001) - 1000;
        }

        // 输出数组前10个元素作为验证

        return  randomArray;
    }
}
