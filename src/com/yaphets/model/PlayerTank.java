package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;

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
        g.drawImage(image, gamePoint.getX(), gamePoint.getY(), image.getWidth(), image.getHeight(), null);
    }

//    private void move() {
//        switch (moveDir) {
//            case MOVE_UP:
//                gamePoint.setY(gamePoint.getY() - GamePropertiesMgr.PLAYER_SPEED);
//                break;
//            case MOVE_DOWN:
//                break;
//            case MOVE_LEFT:
//                break;
//            case MOVE_RIGHT:
//                break;
//            default:
//                break;
//        }
//    }
}
