package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;
import com.yaphets.utils.GamePropertiesMgr;
import com.yaphets.utils.GameResourceMgr;
import com.yaphets.utils.ImageUtil;

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
    protected BufferedImage imageUp, imageDown, imageLeft, imageRight;

    public Tank(int x, int y, MoveDir moveDir, BufferedImage image) {
        gamePoint = new GamePoint<>(x, y);
        this.moveDir = moveDir;
        this.imageUp = image;

        imageDown = ImageUtil.rotateImage(image, 180);
        imageLeft = ImageUtil.rotateImage(image, -90);
        imageRight = ImageUtil.rotateImage(image, 90);
    }

    public Tank(GamePoint<Integer> gamePoint, MoveDir moveDir, BufferedImage image) {
        this(gamePoint.getX(), gamePoint.getY(), moveDir, image);
    }

    /**
     * 子类去实现具体的绘制策略
     *
     * @param g: 画笔
     */
    protected abstract void draw(Graphics g);

    public void paint(Graphics g) {
        draw(g);
    }

    /**
     * 边界线制
     */
    protected void checkBoundary(GamePoint<Integer> gamePoint) {
        int x = Math.max(gamePoint.getX(), 2);
        int y = Math.max(gamePoint.getY(), 28);
        int imageWidth = imageUp.getWidth();
        int imageHeight = imageUp.getHeight();
        x = (x + imageWidth >= GamePropertiesMgr.GAME_WIDTH) ? (GamePropertiesMgr.GAME_WIDTH - imageWidth) : x;
        y = (y + imageHeight >= GamePropertiesMgr.GAME_HEIGHT) ? (GamePropertiesMgr.GAME_HEIGHT - imageHeight) : y;
        gamePoint.setX(x);
        gamePoint.setY(y);
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

    public BufferedImage getImageUp() {
        return imageUp;
    }

    public void setImageUp(BufferedImage imageUp) {
        this.imageUp = imageUp;
    }
}
