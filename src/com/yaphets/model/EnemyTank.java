package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;
import com.yaphets.utils.GameResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author gszqy
 * @date 13:57 2020/11/27
 */
public class EnemyTank extends Tank {
    public EnemyTank(int x, int y, MoveDir moveDir, BufferedImage image) {
        super(x, y, moveDir, image);
    }

    public EnemyTank(GamePoint<Integer> gamePoint, MoveDir moveDir, BufferedImage image) {
        super(gamePoint, moveDir, image);
    }

    @Override
    protected void draw(Graphics g) {
        g.drawImage(image, gamePoint.getX(), gamePoint.getY(), image.getWidth(), image.getHeight(), null);
    }
}