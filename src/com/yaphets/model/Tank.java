package com.yaphets.model;

import com.yaphets.enums.MoveDir;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author gszqy
 * @date 15:54 2020/11/26
 */
public class Tank {
    /**
     * 坦克宽度
     */
    private static final int WIDTH = 50;

    /**
     * 坦克高度
     */
    private static final int HEIGHT = 50;

    /**
     * 坦克移动速度
     */
    private static final int SPEED = 5;

    /**
     * 坦克坐标
     */
    private int x, y;

    /**
     * 坦克移动方向
     */
    private MoveDir moveDir = MoveDir.MOVE_STOP;

    /**
     * 坦克图片
     */
    private BufferedImage image;

    public Tank(int x, int y, MoveDir moveDir, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.moveDir = moveDir;
        this.image = image;
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
