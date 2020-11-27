package com.yaphets.view;

import com.yaphets.control.GameScene;
import com.yaphets.utils.GamePropertiesMgr;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author gszqy
 * @date 14:26 2020/11/26
 */
public class GameView extends Frame {
    /**
     * 场景
     */
    private GameScene scene = new GameScene(this);

    /**
     * 双缓冲离屏渲染画布,实际的绘制都是在该Image上绘制后整体切换显示
     * 消除闪烁
     */
    private Image offScreenImage;

    public GameView() throws HeadlessException {
        init();
        registerListener();
    }

    private void init() {
        setTitle("Tank War");
        setSize(GamePropertiesMgr.GAME_WIDTH, GamePropertiesMgr.GAME_HEIGHT);
        setResizable(false);
        setBackground(Color.black);
        setVisible(true);
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
            offScreenImage = this.createImage(GamePropertiesMgr.GAME_WIDTH, GamePropertiesMgr.GAME_HEIGHT);
        }
        Graphics pen = offScreenImage.getGraphics();
        Color color = pen.getColor();
        pen.setColor(Color.black);
        pen.fillRect(0, 0, GamePropertiesMgr.GAME_WIDTH, GamePropertiesMgr.GAME_HEIGHT);
        pen.setColor(color);
        paint(pen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        scene.paint(g);
    }

    /**
     * 自定义的键盘处理监听
     */
    class ViewKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            scene.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            scene.keyReleased(e);
        }
    }
}
