package com.wxm.pattern.templateMethod.base;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 15:22:57
 */
abstract class Game {
    // 模板方法
    final void play() {
        start();
        playTurn();
        end();
    }

    // 基本操作
    abstract void start();
    abstract void playTurn();
    abstract void end();

    // 钩子方法（Hook Method），可以被子类覆盖
    boolean isEndOfGame() {
        return false;
    }
}