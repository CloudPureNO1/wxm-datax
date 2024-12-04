package com.wxm.pattern.templateMethod.base;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 15:23:11
 */
class Chess extends Game {
    @Override
    void start() {
        System.out.println("Chess game started. Let's play!");
    }

    @Override
    void playTurn() {
        System.out.println("Playing turn...");
    }

    @Override
    void end() {
        System.out.println("Game ended.");
    }
}

