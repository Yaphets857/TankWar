package com.yaphets.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author gszqy
 * @date 22:25 2020/11/26
 */
public class GamePropertiesMgr {
    /**
     * GAME_WIDTH:  游戏界面宽度
     * GAME_HEIGHT: 游戏界面高度
     */
    public static int GAME_WIDTH;
    public static int GAME_HEIGHT;

    /**
     * 玩家移动速度
     */
    public static int PLAYER_SPEED;

    /**
     * 敌人移动速度
     */
    public static int ENEMY_SPEED;

    /**
     * 子弹移动速度
     */
    public static int BULLET_SPEED;

    /**
     * 初始敌人数量
     */
    public static int INIT_ENEMY_CNT;

    /**
     * 开火策略
     */
    public static String PLAYER_FIRE_STRATEGY, ENEMY_FIRE_STRATEGY;

    static {
        Properties properties = new Properties();
        ClassLoader classLoader = GameResourceMgr.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(".config.properties");
        try (inputStream){
            properties.load(inputStream);
            GAME_WIDTH = Integer.parseInt(properties.getProperty("gameWidth", "800"));
            GAME_HEIGHT = Integer.parseInt(properties.getProperty("gameHeight", "600"));
            PLAYER_SPEED = Integer.parseInt(properties.getProperty("playerSpeed", "5"));
            ENEMY_SPEED = Integer.parseInt(properties.getProperty("enemySpeed", "5"));
            BULLET_SPEED = Integer.parseInt(properties.getProperty("bulletSpeed", "10"));
            INIT_ENEMY_CNT = Integer.parseInt(properties.getProperty("initEnemyCnt", "10"));

            PLAYER_FIRE_STRATEGY = properties.getProperty("playerFireStrategy", "com.yaphets.strategy.PlayerFireStrategy");
            ENEMY_FIRE_STRATEGY = properties.getProperty("enemyFireStrategy", "com.yaphets.strategy.EnemyFireStrategy");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GamePropertiesMgr() {
    }
}
