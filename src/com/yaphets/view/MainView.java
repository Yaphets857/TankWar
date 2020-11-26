package com.yaphets.view;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author gszqy
 * @date 14:26 2020/11/26
 */
public class MainView extends Frame {
    /**
     * 窗口宽度
     */
    private static final int WIDTH = 800;

    /**
     * 窗口高度
     */
    private static final int HEIGHT = 600;

    public MainView() throws HeadlessException {
        init();
        registerListener();
    }

    private void registerListener() {
        //添加关闭事件的监听处理
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                super.windowClosing(e);
            }
        });

        addKeyListener(new ViewKeyAdapter());
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");

        super.paint(g);
    }

    private void init() {
        setTitle("Tank War");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
    }

    /**
     * 自定义的键盘处理监听
     */
    static class ViewKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
        }
    }
}
