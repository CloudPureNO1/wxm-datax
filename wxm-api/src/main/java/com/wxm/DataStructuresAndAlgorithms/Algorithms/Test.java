package com.wxm.DataStructuresAndAlgorithms.Algorithms;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-24 9:32:47
 */
public class Test {
    public static void main(String[] args) {
        int [] arr={3,4,6,2,9,0,1};
        // 冒泡排序是把数组的每一个元素与后面的元素都比较一遍，（两个相邻的比较），大的后移，直至最后，比较完成后，最后一个元素是最大的元素
        for(int i=0;i<arr.length-1;i++){ // 训话的次数
            for(int j=0;j<arr.length-1-i;j++){ // 每次循环的数据,用于进行相邻的元素比较
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+"\t");
        }
    }
}
