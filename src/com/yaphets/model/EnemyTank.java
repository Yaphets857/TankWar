package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;
import com.yaphets.utils.GameResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author gszqy
 * @date 13:57 2020/11/27
 */
public class EnemyTank extends Tank {
    private final Random random = new Random();
    private int timeCnt = 0;
    private final int barrelLength = 0;

    public EnemyTank(int x, int y, MoveDir moveDir, BufferedImage image) {
        super(x, y, moveDir, image);
    }

    public EnemyTank(GamePoint<Integer> gamePoint, MoveDir moveDir, BufferedImage image) {
        super(gamePoint, moveDir, image);
    }

    @Override
    protected void move(Graphics g) {
        MoveDir dir = moveDir;
        if (random.nextInt(100) > 90) {
            //-1的原因为去除STOP状态,否则可能随机出停止状态导致坦克半天不移动
            dir = MoveDir.values()[random.nextInt(MoveDir.values().length - 1)];
            setMoveDir(dir);
        }

        if (dir != MoveDir.MOVE_STOP && (timeCnt++ > 20)) {
            timeCnt = 0;
            int x = 0, y = 0;
            BufferedImage bulletImage = GameResourceMgr.bulletImage[dir.ordinal()];
            switch (dir) {
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
            bulletList.add(new Bullet(x, y, dir, GameResourceMgr.bulletImage[dir.ordinal()], this));
        }
    }
}
