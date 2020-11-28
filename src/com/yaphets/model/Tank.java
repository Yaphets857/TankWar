package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;
import com.yaphets.utils.GamePropertiesMgr;
import com.yaphets.utils.GameResourceMgr;
import com.yaphets.utils.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

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

    /**
     * 记录当前的方向图片
     */
    private BufferedImage currImage;

    /**
     * 记录上一次停止前的方向图片
     */
    private BufferedImage lastImage;

    /**
     * 子弹集合
     */
    protected List<Bullet> bulletList = new LinkedList<>();

    /**
     * 炮筒长度
     */
    private final int barrelLength = 0;

    public Tank(int x, int y, MoveDir moveDir, BufferedImage image) {
        gamePoint = new GamePoint<>(x, y);
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
        }else {
            g.drawImage(lastImage, gamePoint.getX(), gamePoint.getY(), lastImage.getWidth(), lastImage.getHeight(), null);
        }

        move(g);
    }

    public void paint(Graphics g) {
        draw(g);
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
        if (moveDir != MoveDir.MOVE_STOP) {
            int x = 0, y = 0;
            BufferedImage bulletImage = GameResourceMgr.bulletImage[moveDir.ordinal()];
            switch (moveDir) {
                case MOVE_UP:
                    x = getGamePoint().getX() + imageUp.getWidth() / 2 - bulletImage.getWidth() / 2;
                    y = getGamePoint().getY() - barrelLength;
                    break;
                case MOVE_RIGHT:
                    x = getGamePoint().getX() + imageUp.getWidth() + barrelLength;
                    y = getGamePoint().getY() + imageUp.getHeight() / 2 - bulletImage.getHeight() / 2;
                    break;
                case MOVE_DOWN:
                    x = getGamePoint().getX() + imageRight.getWidth() / 2 - bulletImage.getWidth() / 2;
                    y = getGamePoint().getY() + barrelLength;
                    break;
                case MOVE_LEFT:
                    x = getGamePoint().getX() - barrelLength;
                    y = getGamePoint().getY() + imageUp.getHeight() / 2 - bulletImage.getHeight() / 2;
                    break;
                default:
                    break;
            }

            bulletList.add(new Bullet(x, y, moveDir, GameResourceMgr.bulletImage[moveDir.ordinal()], this));
        }
    }

    public List<Bullet> getBulletList() {
        return bulletList;
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
