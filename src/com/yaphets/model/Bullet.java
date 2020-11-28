package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;
import com.yaphets.utils.GamePropertiesMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author gszqy
 * @date 20:18 2020/11/27
 */
public class Bullet {
    /**
     * 子弹坐标
     */
    protected GamePoint<Integer> gamePoint;

    /**
     * 子弹移动方向
     */
    protected MoveDir moveDir;

    /**
     * 子弹图片
     */
    protected BufferedImage image;

    private Tank tank;

    public Bullet(int x, int y, MoveDir moveDir, BufferedImage image, Tank tank) {
        gamePoint = new GamePoint<>(x, y);
        this.moveDir = moveDir;
        this.image = image;
        this.tank = tank;
    }

    public Bullet(GamePoint<Integer> gamePoint, MoveDir moveDir, BufferedImage image, Tank tank) {
        this(gamePoint.getX(), gamePoint.getY(), moveDir, image, tank);
    }

    public void paint(Graphics g) {

        if (checkBoundary()) {
            switch (moveDir) {
                case MOVE_UP:
                    gamePoint.setY(gamePoint.getY() - GamePropertiesMgr.BULLET_SPEED);
                    break;
                case MOVE_DOWN:
                    gamePoint.setY(gamePoint.getY() + GamePropertiesMgr.BULLET_SPEED);
                    break;
                case MOVE_LEFT:
                    gamePoint.setX(gamePoint.getX() - GamePropertiesMgr.BULLET_SPEED);
                    break;
                case MOVE_RIGHT:
                    gamePoint.setX(gamePoint.getX() + GamePropertiesMgr.BULLET_SPEED);
                    break;
                default:
                    break;
            }
            g.drawImage(image, gamePoint.getX(), gamePoint.getY(), image.getWidth(), image.getHeight(), null);
        } else {
            tank.getBulletList().remove(this);
        }
    }

    private boolean checkBoundary() {
        int x = gamePoint.getX();
        int y = gamePoint.getY();
        if (x < 0 || x > GamePropertiesMgr.GAME_WIDTH ||
                y < 0 || y > GamePropertiesMgr.GAME_HEIGHT) {
            return false;
        }

        return true;
    }

    public GamePoint<Integer> getGamePoint() {
        return gamePoint;
    }

    public void setGamePoint(GamePoint<Integer> gamePoint) {
        this.gamePoint = gamePoint;
    }

    public MoveDir getMoveDir() {
        return moveDir;
    }

    public void setMoveDir(MoveDir moveDir) {
        this.moveDir = moveDir;
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "gamePoint=" + gamePoint +
                ", moveDir=" + moveDir +
                '}';
    }
}
