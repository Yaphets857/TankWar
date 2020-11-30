package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;
import com.yaphets.factory.GameObjectFactory;
import com.yaphets.strategy.FireStrategy;
import com.yaphets.utils.GamePropertiesMgr;
import com.yaphets.utils.ImageUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gszqy
 * @date 15:54 2020/11/26
 */
public abstract class Tank extends GameObject{
    /**
     * 坦克移动方向
     */
    protected MoveDir moveDir;

    /**
     * 坦克图片
     */
    protected BufferedImage imageUp, imageDown, imageLeft, imageRight;

    /**
     * 记录当前的方向图片
     */
    private BufferedImage currImage;

    /**
     * 记录上一次停止前的方向图片
     */
    private BufferedImage lastImage;

    /**
     * 炮筒长度
     */
    private final int barrelLength = 0;

    private boolean isLiving = true;

    /**
     * fire策略引用
     */
    protected FireStrategy fireStrategy;

    public Tank(int x, int y, MoveDir moveDir, BufferedImage image) {
        super(x, y);
        this.moveDir = moveDir;
        this.imageUp = image;

        imageDown = ImageUtil.rotateImage(image, 180);
        imageLeft = ImageUtil.rotateImage(image, -90);
        imageRight = ImageUtil.rotateImage(image, 90);
        currImage = lastImage = imageUp;
    }

    public Tank(GamePoint<Integer> gamePoint, MoveDir moveDir, BufferedImage image) {
        this(gamePoint.getX(), gamePoint.getY(), moveDir, image);
    }

    protected void draw(Graphics g) {
        switch (moveDir) {
            case MOVE_UP:
                currImage = imageUp;
                gamePoint.setY(gamePoint.getY() - GamePropertiesMgr.PLAYER_SPEED);
                break;
            case MOVE_DOWN:
                currImage = imageDown;
                gamePoint.setY(gamePoint.getY() + GamePropertiesMgr.PLAYER_SPEED);
                break;
            case MOVE_LEFT:
                currImage = imageLeft;
                gamePoint.setX(gamePoint.getX() - GamePropertiesMgr.PLAYER_SPEED);
                break;
            case MOVE_RIGHT:
                currImage = imageRight;
                gamePoint.setX(gamePoint.getX() + GamePropertiesMgr.PLAYER_SPEED);
                break;
            default:
                lastImage = currImage;
                break;
        }

        checkBoundary(gamePoint);
        if (currImage != null) {
            g.drawImage(currImage, gamePoint.getX(), gamePoint.getY(), currImage.getWidth(), currImage.getHeight(), null);
        } else {
            g.drawImage(lastImage, gamePoint.getX(), gamePoint.getY(), lastImage.getWidth(), lastImage.getHeight(), null);
        }

        move(g);
    }

    @Override
    public void paint(Graphics g) {
        if (!isLiving) {
            GameObjectFactory.getInstance().createExplode(gamePoint.getX(), gamePoint.getY());
            GameModelMgr.getInstance().remove(this);
        } else {
            draw(g);
        }
    }

    /**
     * 子类去实现具体的移动策略
     *
     * @param g: 画笔
     */
    protected abstract void move(Graphics g);

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

    /**
     * fire：用于发射子弹
     */
    public void fire() {
        if (fireStrategy != null && moveDir != MoveDir.MOVE_STOP) {
            fireStrategy.fire(this);
        }
    }

    /**
     * 处理键盘按下事件
     *
     * @param e 键盘事件
     */
    public void keyPressed(KeyEvent e) {
    }

    /**
     * 处理键盘松开事件
     *
     * @param e 键盘事件
     */
    public void keyRelease(KeyEvent e) {
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

    public Rectangle getRectangle() {
        return new Rectangle(gamePoint.getX(), gamePoint.getY(), currImage.getWidth(), currImage.getHeight());
    }

    public void die() {
        isLiving = false;
    }
}
