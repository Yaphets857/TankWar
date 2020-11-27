package com.yaphets.main;

import com.yaphets.view.GameView;

/**
 * @author gszqy
 * @date 14:23 2020/11/26
 */
public class Start {
    public static void main(String[] args) {
        GameView view = new GameView();

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            view.repaint();
        }

    }

}
