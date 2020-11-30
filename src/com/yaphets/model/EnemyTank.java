package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;
import com.yaphets.strategy.FireStrategy;
import com.yaphets.utils.GamePropertiesMgr;

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

    public EnemyTank(int x, int y, MoveDir moveDir, BufferedImage image) {
        super(x, y, moveDir, image);
        try {
            fireStrategy = (FireStrategy) Class.forName(GamePropertiesMgr.ENEMY_FIRE_STRATEGY).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EnemyTank(GamePoint<Integer> gamePoint, MoveDir moveDir, BufferedImage image) {
        super(gamePoint, moveDir, image);
        try {
            fireStrategy = (FireStrategy) Class.forName(GamePropertiesMgr.ENEMY_FIRE_STRATEGY).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            fire();
        }
    }
}
