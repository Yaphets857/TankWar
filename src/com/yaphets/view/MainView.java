package com.yaphets.view;

import com.yaphets.factory.TankFactory;
import com.yaphets.model.Tank;
import com.yaphets.utils.GamePropertiesMgr;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

/**
 * @author gszqy
 * @date 14:26 2020/11/26
 */
public class MainView extends Frame {
    /**
     * 双缓冲离屏渲染画布,实际的绘制都是在该Image上绘制后整体切换显示
     * 消除闪烁
     */
    private Image offScreenImage;

    private final Random random = new Random();

    public MainView() throws HeadlessException {
        initUI();
        initPlayer();
        initEnemys();
        registerListener();
    }

    private void initUI() {
        setTitle("Tank War");
        setSize(GamePropertiesMgr.GAME_WIDTH, GamePropertiesMgr.GAME_HEIGHT);
        setResizable(false);
        setVisible(true);
    }

    private void initPlayer() {
        TankFactory.getInstance().createPlayers(random.nextInt(GamePropertiesMgr.GAME_WIDTH), random.nextInt(GamePropertiesMgr.GAME_HEIGHT));
        TankFactory.getInstance().createPlayers(random.nextInt(GamePropertiesMgr.GAME_WIDTH), random.nextInt(GamePropertiesMgr.GAME_HEIGHT));
    }

    private void initEnemys() {
        for (int i = 0; i < GamePropertiesMgr.INIT_ENEMY_CNT; i++) {
            TankFactory.getInstance().createEnemys(random.nextInt(GamePropertiesMgr.GAME_WIDTH), random.nextInt(GamePropertiesMgr.GAME_HEIGHT));
        }
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
        dispPlayer(g);
        dispEnemys(g);
        dispGameMsg(g);
    }

    private void dispEnemys(Graphics g) {
        for (Tank t : TankFactory.getInstance().getEnemys()) {
            t.paint(g);
        }
    }

    private void dispPlayer(Graphics g) {
        for (Tank t : TankFactory.getInstance().getPlayers()) {
            t.paint(g);
        }
    }

    private void dispGameMsg(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("当前玩家数量: " + TankFactory.getInstance().getPlayers().size(), 40, 40);
        g.drawString("当前敌人数量: " + TankFactory.getInstance().getEnemys().size(), 40, 60);
        g.setColor(color);
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
