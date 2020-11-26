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

    /**
     * 双缓冲离屏渲染画布,实际的绘制都是在该Image上绘制后整体切换显示
     * 消除闪烁
     */
    private Image offScreenImage;

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

    /**
     * 重写update使用双缓冲消除闪烁
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(WIDTH, HEIGHT);
        }
        Graphics pen = offScreenImage.getGraphics();
        Color color = pen.getColor();
        pen.setColor(Color.black);
        pen.fillRect(0, 0, WIDTH, HEIGHT);
        pen.setColor(color);
        paint(pen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
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
