package com.yaphets.control;

import com.yaphets.enums.MoveDir;
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
    private Random random = new Random();

    /**
     * 是否被按下
     */
    private boolean bUp, bDown, bLeft, bRight;


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
        paintPlayers(g);
        paintEnemys(g);
        paintGameMessage(g);
    }

    private void paintEnemys(Graphics g) {
        List<Tank> enemyes = TankFactory.getInstance().getEnemys();
        for (Tank t : enemyes) {
            t.paint(g);
            for (Bullet bullet : t.getBulletList()) {
                if (bullet != null) {
                    bullet.paint(g);
                }
            }
        }
    }

    private void paintPlayers(Graphics g) {
        for (Tank t : TankFactory.getInstance().getPlayers()) {
            t.paint(g);
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
        g.drawString("当前子弹数量: " + totalBullets, 40, 80);
        g.setColor(color);
    }


    /**
     * 管理键盘按下事件响应处理
     */
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                bUp = true;
                break;
            case KeyEvent.VK_DOWN:
                bDown = true;
                break;
            case KeyEvent.VK_LEFT:
                bLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                bRight = true;
                break;
            default:
                break;
        }

        MoveDir dir = MoveDir.MOVE_STOP;
        if (bUp) {
            dir = MoveDir.MOVE_UP;
        }
        if (bDown) {
            dir = MoveDir.MOVE_DOWN;
        }
        if (bLeft) {
            dir = MoveDir.MOVE_LEFT;
        }
        if (bRight) {
            dir = MoveDir.MOVE_RIGHT;
        }

        TankFactory.getInstance().getPlayers().get(0).setMoveDir(dir);
    }

    /**
     * 管理键盘松开事件响应处理
     */
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                bUp = false;
                break;
            case KeyEvent.VK_DOWN:
                bDown = false;
                break;
            case KeyEvent.VK_LEFT:
                bLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                bRight = false;
                break;
            default:
                break;
        }

        MoveDir dir = MoveDir.MOVE_STOP;

        if (bUp) {
            dir = MoveDir.MOVE_UP;
        }
        if (bDown) {
            dir = MoveDir.MOVE_DOWN;
        }
        if (bLeft) {
            dir = MoveDir.MOVE_LEFT;
        }
        if (bRight) {
            dir = MoveDir.MOVE_RIGHT;
        }

        TankFactory.getInstance().getPlayers().get(0).setMoveDir(dir);
    }

}
