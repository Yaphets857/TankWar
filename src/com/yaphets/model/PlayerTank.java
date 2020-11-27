package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;
import com.yaphets.utils.GamePropertiesMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author gszqy
 * @date 13:49 2020/11/27
 */
public class PlayerTank extends Tank {
    public PlayerTank(int x, int y, MoveDir moveDir, BufferedImage image) {
        super(x, y, moveDir, image);
    }

    public PlayerTank(GamePoint<Integer> gamePoint, MoveDir moveDir, BufferedImage image) {
        super(gamePoint, moveDir, image);
    }

    @Override
    protected void draw(Graphics g) {
        move(g);
    }

    private void move(Graphics g) {
        BufferedImage image = null;
        switch (moveDir) {
            case MOVE_UP:
                image = imageUp;
                gamePoint.setY(gamePoint.getY() - GamePropertiesMgr.PLAYER_SPEED);
                break;
            case MOVE_DOWN:
                image = imageDown;
                gamePoint.setY(gamePoint.getY() + GamePropertiesMgr.PLAYER_SPEED);
                break;
            case MOVE_LEFT:
                image = imageLeft;
                gamePoint.setX(gamePoint.getX() - GamePropertiesMgr.PLAYER_SPEED);
                break;
            case MOVE_RIGHT:
                image = imageRight;
                gamePoint.setX(gamePoint.getX() + GamePropertiesMgr.PLAYER_SPEED);
                break;
            default:
                break;
        }

        checkBoundary(gamePoint);
        if (image != null) {
            g.drawImage(image, gamePoint.getX(), gamePoint.getY(), image.getWidth(), image.getHeight(), null);
        }
    }
}
