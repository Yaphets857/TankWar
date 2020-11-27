package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;
import com.yaphets.utils.GamePropertiesMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author gszqy
 * @date 15:54 2020/11/26
 */
public abstract class Tank {
    /**
     * 坦克坐标
     */
    protected GamePoint<Integer> gamePoint;

    /**
     * 坦克移动方向
     */
    protected MoveDir moveDir;

    /**
     * 坦克图片
     */
    protected BufferedImage image;

    public Tank(int x, int y, MoveDir moveDir, BufferedImage image) {
        gamePoint = new GamePoint<>(x, y);
        this.moveDir = moveDir;
        this.image = image;
    }

    public Tank(GamePoint<Integer> gamePoint, MoveDir moveDir, BufferedImage image) {
        this(gamePoint.getX(), gamePoint.getY(), moveDir, image);
    }

    /**
     * 子类去实现具体的绘制策略
     * @param g: 画笔
     */
    protected abstract void draw(Graphics g);

    public void paint(Graphics g) {
        draw(g);
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

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
