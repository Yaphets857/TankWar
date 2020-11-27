package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author gszqy
 * @date 13:57 2020/11/27
 */
public class EnemyTank extends Tank {
    private final Random random = new Random();

    public EnemyTank(int x, int y, MoveDir moveDir, BufferedImage image) {
        super(x, y, moveDir, image);
    }

    public EnemyTank(GamePoint<Integer> gamePoint, MoveDir moveDir, BufferedImage image) {
        super(gamePoint, moveDir, image);
    }

    @Override
    protected void move(Graphics g) {
        if (random.nextInt(100) > 95) {
            setMoveDir(MoveDir.values()[random.nextInt(MoveDir.values().length)]);
        }
    }
}
