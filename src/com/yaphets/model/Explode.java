package com.yaphets.model;

import com.yaphets.domain.GamePoint;
import com.yaphets.utils.GameResourceMgr;

import java.awt.*;

/**
 * @author gszqy
 * @date 17:37 2020/11/28
 */
public class Explode extends GameObject{
    /**
     * 播放计数器
     */
    private int count;

    public Explode(int x, int y) {
        super(x, y);
        GameModelMgr.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(GameResourceMgr.explodeImages[count++], gamePoint.getX(), gamePoint.getY(), null);
        if (count >= GameResourceMgr.explodeImages.length) {
            GameModelMgr.getInstance().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Explode{" +
                "gamePoint=" + gamePoint +
                '}';
    }
}
