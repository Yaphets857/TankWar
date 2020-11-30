package com.yaphets.collide;

import com.yaphets.enums.Camp;
import com.yaphets.model.Bullet;
import com.yaphets.model.EnemyTank;
import com.yaphets.model.GameObject;
import com.yaphets.model.Tank;

/**
 * @author gszqy
 * @date 00:12 2020/12/1
 * 坦克子弹碰撞检测
 */
public class TankBulletCollider implements Collidetor {

    @Override
    public boolean isCollided(GameObject o1, GameObject o2) {
        if (o1 instanceof EnemyTank && o2 instanceof Bullet) {
            EnemyTank tank = (EnemyTank) o1;
            Bullet bullet = (Bullet) o2;

            if (bullet.getCamp() == Camp.PLAYER && tank.getRectangle().intersects(bullet.getRectange())) {
                tank.die();
                bullet.die();
                return true;
            }
            return false;
        } else if (o1 instanceof Bullet && o2 instanceof EnemyTank) {
            return isCollided(o2, o1);
        }
        return false;
    }
}
