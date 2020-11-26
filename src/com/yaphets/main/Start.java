package com.yaphets.main;

import com.yaphets.utils.GameResourceMgr;
import com.yaphets.view.MainView;

/**
 * @author gszqy
 * @date 14:23 2020/11/26
 */
public class Start {
    public static void main(String[] args) {
        MainView view = new MainView();

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
