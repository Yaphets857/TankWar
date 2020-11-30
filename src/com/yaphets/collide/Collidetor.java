package com.yaphets.collide;

import com.yaphets.model.GameObject;

/**
 * @author gszqy
 * @date 00:07 2020/12/1
 */
public interface Collidetor {
    boolean isCollided(GameObject o1, GameObject o2);
}
