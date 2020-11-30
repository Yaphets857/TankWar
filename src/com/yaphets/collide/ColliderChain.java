package com.yaphets.collide;

import com.yaphets.model.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author gszqy
 * @date 00:14 2020/12/1
 * 碰撞检测链(责任链模式)
 */
public class ColliderChain implements Collidetor {
    private List<Collidetor> collidetors = new LinkedList<>();

    public ColliderChain() {
        add(new TankBulletCollider());
        add(new TankTankCollider());
    }

    public void add(Collidetor collidetor) {
        collidetors.add(collidetor);
    }

    @Override
    public boolean isCollided(GameObject o1, GameObject o2) {
        for (Collidetor c : collidetors) {
            if (c.isCollided(o1, o2)) {
                return true;
            }
        }
        return false;
    }
}
