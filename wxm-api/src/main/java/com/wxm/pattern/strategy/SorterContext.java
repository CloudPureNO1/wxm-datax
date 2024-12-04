package com.wxm.pattern.strategy;

/**
 * 环境角色
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 13:22:12
 */
public class SorterContext {
    private SortingStrategy strategy;

    public SorterContext(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void sort(int[] numbers) {
        strategy.sort(numbers);
    }
}
