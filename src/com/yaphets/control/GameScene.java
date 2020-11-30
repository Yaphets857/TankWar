package com.yaphets.control;

import com.yaphets.collide.ColliderChain;
import com.yaphets.model.Bullet;
import com.yaphets.model.GameModelMgr;
import com.yaphets.model.Tank;
import com.yaphets.view.GameView;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author gszqy
 * @date 10:44 2020/11/27
 * 游戏场景：用于管理图元、控制图元移动
 */
public class GameScene {
    private GameView view;

    public GameScene(GameView view) {
        this.view = view;
    }

    /**
     * 管理图元绘制
     */
    public void paint(Graphics g) {
        GameModelMgr.getInstance().paint(g);
    }

    /**
     * 管理键盘按下事件响应处理
     */
    public void keyPressed(KeyEvent e) {
        for (Tank tank : GameModelMgr.getInstance().getPlayers()) {
            tank.keyPressed(e);
        }
    }

    /**
     * 管理键盘松开事件响应处理
     */
    public void keyReleased(KeyEvent e) {
        for (Tank tank : GameModelMgr.getInstance().getPlayers()) {
            tank.keyRelease(e);
        }
    }
}
