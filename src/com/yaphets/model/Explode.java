package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.factory.TankFactory;
import com.yaphets.utils.GameResourceMgr;

import java.awt.*;

/**
 * @author gszqy
 * @date 17:37 2020/11/28
 */
public class Explode {
    /**
     * 爆炸坐标
     */
    private GamePoint<Integer> gamePoint;

    /**
     * 播放计数器
     */
    private int count;

    public Explode(GamePoint<Integer> gamePoint) {
        this.gamePoint = gamePoint;
    }

    public void paint(Graphics g) {
        g.drawImage(GameResourceMgr.explodeImages[count++], gamePoint.getX(), gamePoint.getY(), null);
        if (count >= GameResourceMgr.explodeImages.length) {
            TankFactory.getInstance().getExplodes().remove(this);
        }
    }

    public GamePoint<Integer> getGamePoint() {
        return gamePoint;
    }

    public void setGamePoint(GamePoint<Integer> gamePoint) {
        this.gamePoint = gamePoint;
    }

    @Override
    public String toString() {
        return "Explode{" +
                "gamePoint=" + gamePoint +
                '}';
    }
}
