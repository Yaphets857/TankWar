package com.yaphets.model;

import com.yaphets.domain.GamePoint;

import java.awt.*;

/**
 * @author gszqy
 * @date 21:49 2020/11/30
 */
public abstract class GameObject {
    private int x, y;
    protected GamePoint<Integer> gamePoint;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.gamePoint = new GamePoint<>(x, y);
    }

    public GameObject(GamePoint<Integer> gamePoint) {
        this.gamePoint = gamePoint;
        this.x = gamePoint.getX();
        this.y = gamePoint.getY();
    }

    public abstract void paint(Graphics g);

    public GamePoint<Integer> getGamePoint() {
        return gamePoint;
    }

    public void setGamePoint(GamePoint<Integer> gamePoint) {
        this.gamePoint = gamePoint;
    }

    @Override
    public String toString() {
        return "GameObject{" +
                "gamePoint=" + gamePoint +
                '}';
    }
}
