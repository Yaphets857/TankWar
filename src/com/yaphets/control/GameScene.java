package com.yaphets.control;

import com.yaphets.factory.TankFactory;
import com.yaphets.model.Bullet;
import com.yaphets.model.Tank;
import com.yaphets.utils.GamePropertiesMgr;
import com.yaphets.view.GameView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

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
        init();
    }

    private void init() {
        initPlayers();
        initEnemys();
    }

    /**
     * 初始化玩家
     */
    private void initPlayers() {
        TankFactory.getInstance().createPlayers(random.nextInt(GamePropertiesMgr.GAME_WIDTH), random.nextInt(GamePropertiesMgr.GAME_HEIGHT));
//        TankFactory.getInstance().createPlayers(random.nextInt(GamePropertiesMgr.GAME_WIDTH), random.nextInt(GamePropertiesMgr.GAME_HEIGHT));
    }

    /**
     * 初始化敌人
     */
    private void initEnemys() {
        for (int i = 0; i < GamePropertiesMgr.INIT_ENEMY_CNT; i++) {
            TankFactory.getInstance().createEnemys(random.nextInt(GamePropertiesMgr.GAME_WIDTH), random.nextInt(GamePropertiesMgr.GAME_HEIGHT));
        }
    }

    /**
     * 管理图元绘制
     */
    public void paint(Graphics g) {
        paintTanks(TankFactory.getInstance().getPlayers(), g);
        paintTanks(TankFactory.getInstance().getEnemys(), g);

        paintExplodes(g);
        paintGameMessage(g);

        collideDetect();
    }

    /**
     * 绘制坦克
     * @param tanks 具体的坦克列表(玩家or敌人)
     * @param g 画笔
     */
    private void paintTanks(List<Tank> tanks, Graphics g) {
        for (int i = 0; i < tanks.size(); ++i) {
            Tank t = tanks.get(i);
            t.paint(g);
            for (int j = 0; j < t.getBulletList().size(); ++j) {
                t.getBulletList().get(j).paint(g);
            }
        }
    }

    private void paintExplodes(Graphics g) {
        for (int i = 0; i < TankFactory.getInstance().getExplodes().size(); ++i) {
            TankFactory.getInstance().getExplodes().get(i).paint(g);
        }
    }

    /**
     * 碰撞检测
     */
    private void collideDetect() {
        List<Tank> enemys = TankFactory.getInstance().getEnemys();
        List<Tank> players = TankFactory.getInstance().getPlayers();

        label:for (Tank player : players) {
            for (Bullet bullet : player.getBulletList()) {
                for (Tank ememy : enemys) {
                    if (bullet.getRectange().intersects(ememy.getRectangle())) {
                        bullet.die();
                        ememy.die();
                        break;
                    }
                }
            }
        }
    }

    private void paintGameMessage(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("当前玩家数量: " + TankFactory.getInstance().getPlayers().size(), 40, 40);
        g.drawString("当前敌人数量: " + TankFactory.getInstance().getEnemys().size(), 40, 60);
        int totalBullets = 0;
        for (Tank tank : TankFactory.getInstance().getEnemys()) {
            totalBullets += tank.getBulletList().size();
        }
        for (Tank tank : TankFactory.getInstance().getPlayers()) {
            totalBullets += tank.getBulletList().size();
        }

        g.drawString("当前子弹数量: " + totalBullets, 40, 80);
        g.setColor(color);
    }


    /**
     * 管理键盘按下事件响应处理
     */
    public void keyPressed(KeyEvent e) {
        for (Tank tank : TankFactory.getInstance().getPlayers()) {
            tank.keyPressed(e);
        }
    }

    /**
     * 管理键盘松开事件响应处理
     */
    public void keyReleased(KeyEvent e) {
        for (Tank tank : TankFactory.getInstance().getPlayers()) {
            tank.keyRelease(e);
        }
    }
}
