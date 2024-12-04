package com.wxm.DataStructuresAndAlgorithms.Algorithms;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-23 16:45:25
 */
public class SelectionSort {

    // 主函数，程序执行的入口
    public static void main(String[] args) {
        // 初始化一个整型数组arr，包含5个元素
        int[] arr = {64, 25, 12, 22, 11};

        // 调用selectionSort方法对数组arr进行排序
        selectionSort(arr);

        // 输出提示信息
        System.out.println("Sorted array: ");

        // 调用printArray方法打印排序后的数组
        printArray(arr);
    }


    /**
     * 选择排序（Selection Sort）的时间复杂度分析如下：
     *
     * 最坏情况、平均情况和最佳情况
     * 无论是最坏情况、平均情况还是最佳情况，选择排序的时间复杂度均为 O(n^2)，其中 n 是数组的长度。这是因为选择排序的算法结构包含两个主要的循环：
     *
     * 外部循环：遍历数组中的每个元素，将其视为当前需要定位的元素。
     * 内部循环：在剩余未排序的元素中找到最小（或最大，取决于排序方向）的元素。
     * 对于每个外部循环的迭代，内部循环都要遍历剩余的未排序元素，寻找下一个最小的元素。当外部循环进行到第 i 次迭代时，内部循环需要进行 n-i 次比较。因此，整个算法的比较次数是 (n-1) + (n-2) + ... + 1，这是一个等差数列求和问题，其和为 n*(n-1)/2。
     *
     * 时间复杂度公式
     * 总比较次数：n*(n-1)/2
     * 总交换次数：n-1（每次找到最小元素后，最多进行一次交换）
     * 由于比较次数是主导因素，选择排序的时间复杂度可以简化为 O(n^2)。
     *
     * 空间复杂度
     * 选择排序的空间复杂度为 O(1)，因为它是原地排序算法，不需要额外的存储空间，除了用于交换的少数几个辅助变量。
     *
     * 总结
     * 选择排序的时间复杂度在所有情况下都是 O(n^2)，这使得它在处理大量数据时效率较低，但对于小型数据集或教学目的来说，它是一个简单易懂的排序算法。
     * @param arr
     */
    // 定义一个静态方法selectionSort，参数是一个整型数组
    static void selectionSort(int[] arr) {
        // 获取数组长度并赋值给变量n
        int n = arr.length;

        // 外层循环，从数组的第一个元素开始遍历至倒数第二个元素
        for (int i = 0; i < n - 1; i++) {

            // 假设当前外层循环的索引i处的元素是最小的
            int minIndex = i;

            // 内层循环，从当前外层循环索引i的下一个元素开始，遍历至数组末尾
            for (int j = i + 1; j < n; j++) {

                // 如果找到更小的元素，则更新minIndex为该元素的索引
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // 交换当前外层循环索引i处的元素与最小元素
            // 如果minIndex没有变化（即minIndex == i），则不会发生交换
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // 定义一个静态方法printArray，参数是一个整型数组
    static void printArray(int[] arr) {

        // 使用增强for循环遍历数组中的每一个元素并打印，每个元素后面跟一个空格
        for (int value : arr) {
            System.out.print(value + " ");
        }

        // 打印换行符，以结束一行输出
        System.out.println();
    }
}
