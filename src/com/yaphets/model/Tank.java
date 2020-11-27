package com.yaphets.model;

import com.yaphets.domain.GamePoint;
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
    private GamePoint<Integer> gamePoint;

    /**
     * 坦克移动方向
     */
    private MoveDir moveDir = MoveDir.MOVE_STOP;

    /**
     * 坦克图片
     */
    private BufferedImage image;

    public Tank(int x, int y, MoveDir moveDir, BufferedImage image) {
        gamePoint = new GamePoint<>(x, y);
        this.moveDir = moveDir;
        this.image = image;
    }

    public Tank(GamePoint<Integer> gamePoint, MoveDir moveDir, BufferedImage image) {
        this(gamePoint.getX(), gamePoint.getY(), moveDir, image);
    }

    public void paint(Graphics g) {
        g.drawImage(image, gamePoint.getX(), gamePoint.getY(), WIDTH, HEIGHT, null);
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
