package com.yaphets.strategy;

import com.yaphets.model.Tank;

/**
 * @author gszqy
 * @date 21:08 2020/11/28
 */
public interface FireStrategy {
    /**
     *
     * @param tank
     */
    void fire(Tank tank);
}
