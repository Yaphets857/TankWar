package com.yaphets.collide;

import com.yaphets.model.GameObject;

/**
 * @author gszqy
 * @date 00:13 2020/12/1
 */
public class TankTankCollider implements Collidetor{
    @Override
    public boolean isCollided(GameObject o1, GameObject o2) {
        return false;
    }
}
