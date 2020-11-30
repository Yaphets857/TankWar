package com.yaphets.control;

import com.yaphets.model.Bullet;
import com.yaphets.model.GameModelMgr;
import com.yaphets.model.Tank;
import com.yaphets.view.GameView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.List;

/**
 * @author gszqy
 * @date 10:44 2020/11/27
 * 游戏场景：用于管理图元、控制图元移动
 */
public class GameScene {
    private GameView view;
    private final Random random = new Random();

    public GameScene(GameView view) {
        this.view = view;
    }

    /**
     * 管理图元绘制
     */
    public void paint(Graphics g) {
        GameModelMgr.getInstance().paint(g);

        //TODO: 下一步重构碰撞
        //collideDetect();
    }

    /**
     * 碰撞检测
     */
    private void collideDetect() {
        List<Tank> enemys = GameModelMgr.getInstance().getEnemys();
        List<Tank> players = GameModelMgr.getInstance().getPlayers();

        for (int i = 0; i < players.size(); ++i) {
            List<Bullet> bullets = GameModelMgr.getInstance().getBullets();
            for (int j = 0; j < bullets.size(); ++j) {
                for (int k = 0; k < enemys.size(); ++k) {
                    if (bullets.get(j).getRectange().intersects(enemys.get(k).getRectangle())) {
                        bullets.get(j).die();
                        enemys.get(k).die();
                        return;
                    }
                }
            }
        }
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
