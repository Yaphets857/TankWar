package com.yaphets.strategy;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;
import com.yaphets.model.Bullet;
import com.yaphets.model.Tank;
import com.yaphets.utils.GameResourceMgr;

/**
 * @author gszqy
 * @date 21:11 2020/11/28
 */
public class PlayerFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        if (tank != null) {
            int x = tank.getGamePoint().getX();
            int y = tank.getGamePoint().getY();
            int tankWidth = GameResourceMgr.normalEnemyImageUp.getWidth();
            int tankHeight = GameResourceMgr.normalEnemyImageUp.getHeight();
            int bulletWidth = GameResourceMgr.bulletImages[0].getWidth();
            int bulletHeight = GameResourceMgr.bulletImages[0].getHeight();

            x = x + tankWidth / 2 - bulletWidth / 2;
            y = y + tankHeight / 2 - bulletHeight / 2;
            MoveDir[] values = MoveDir.values();
            for (int i = 0; i < values.length - 1; ++i) {
                new Bullet(new GamePoint<Integer>(x, y), values[i], tank);
            }
        }
    }
}
