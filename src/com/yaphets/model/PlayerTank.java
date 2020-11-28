package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;
import com.yaphets.factory.TankFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * @author gszqy
 * @date 13:49 2020/11/27
 */
public class PlayerTank extends Tank {
    /**
     * 是否被按下
     */
    private boolean bUp, bDown, bLeft, bRight;

    public PlayerTank(int x, int y, MoveDir moveDir, BufferedImage image) {
        super(x, y, moveDir, image);
    }

    public PlayerTank(GamePoint<Integer> gamePoint, MoveDir moveDir, BufferedImage image) {
        super(gamePoint, moveDir, image);
    }

    @Override
    protected void move(Graphics g) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                bUp = true;
                break;
            case KeyEvent.VK_DOWN:
                bDown = true;
                break;
            case KeyEvent.VK_LEFT:
                bLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                bRight = true;
                break;
            default:
                break;
        }

        MoveDir dir = MoveDir.MOVE_STOP;
        if (bUp) {
            dir = MoveDir.MOVE_UP;
        }
        if (bDown) {
            dir = MoveDir.MOVE_DOWN;
        }
        if (bLeft) {
            dir = MoveDir.MOVE_LEFT;
        }
        if (bRight) {
            dir = MoveDir.MOVE_RIGHT;
        }

        setMoveDir(dir);
    }

    @Override
    public void keyRelease(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                bUp = false;
                break;
            case KeyEvent.VK_DOWN:
                bDown = false;
                break;
            case KeyEvent.VK_LEFT:
                bLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                bRight = false;
                break;
            case KeyEvent.VK_SPACE:
                fire();
                break;
            default:
                break;
        }

        MoveDir dir = MoveDir.MOVE_STOP;

        if (bUp) {
            dir = MoveDir.MOVE_UP;
        }
        if (bDown) {
            dir = MoveDir.MOVE_DOWN;
        }
        if (bLeft) {
            dir = MoveDir.MOVE_LEFT;
        }
        if (bRight) {
            dir = MoveDir.MOVE_RIGHT;
        }

        setMoveDir(dir);
    }

    @Override
    protected void beforeDieToDo() {
        TankFactory.getInstance().getPlayers().remove(this);
    }
}
