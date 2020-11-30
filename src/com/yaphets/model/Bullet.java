package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;
import com.yaphets.utils.GamePropertiesMgr;
import com.yaphets.utils.GameResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author gszqy
 * @date 20:18 2020/11/27
 */
public class Bullet extends GameObject{
    /**
     * 子弹移动方向
     */
    protected MoveDir moveDir;

    /**
     * 子弹图片
     */
    protected BufferedImage image;

    public Bullet(int x, int y, MoveDir dir) {
        super(x, y);
        moveDir = dir;
        image = GameResourceMgr.bulletImages[moveDir.ordinal()];
        GameModelMgr.getInstance().add(this);
    }

    public Bullet(GamePoint<Integer> gamePoint, MoveDir dir) {
        this(gamePoint.getX(), gamePoint.getY(), dir);
    }

    @Override
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
            GameModelMgr.getInstance().remove(this);
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

    public MoveDir getMoveDir() {
        return moveDir;
    }

    public void setMoveDir(MoveDir moveDir) {
        this.moveDir = moveDir;
    }

    public Rectangle getRectange() {
        return new Rectangle(gamePoint.getX(), gamePoint.getY(), image.getWidth(), image.getHeight());
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "gamePoint=" + gamePoint +
                ", moveDir=" + moveDir +
                '}';
    }

    public void die() {
        GameModelMgr.getInstance().remove(this);
    }
}
