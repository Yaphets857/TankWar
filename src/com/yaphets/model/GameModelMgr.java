package com.yaphets.model;

import com.yaphets.factory.GameObjectFactory;
import com.yaphets.utils.GamePropertiesMgr;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author gszqy
 * @date 21:47 2020/11/30
 */
public class GameModelMgr {
    static GameModelMgr instance = null;
    /**
     * 所有游戏图元的列表
     */
    private List<GameObject> gameObjects = new LinkedList<>();

    /**
     * 坦克类玩家图元列表
     */
    private List<Tank> players = new LinkedList<>();

    /**
     * 坦克类敌人图元列表
     */
    private List<Tank> enemys = new LinkedList<>();

    /**
     * 子弹类游戏图元列表
     */
    private List<Bullet> bullets = new LinkedList<>();

    /**
     * 爆炸类游戏图元列表
     */
    private List<Explode> explodes = new LinkedList<>();

    private GameModelMgr() {
    }

    public static GameModelMgr getInstance() {
        if (instance == null) {
            synchronized (GameModelMgr.class) {
                if (instance == null) {
                    instance = new GameModelMgr();
                    init();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化玩家、敌人
     */
    private static void init() {
        Random random = new Random();
        GameObjectFactory gameObjectFactory = GameObjectFactory.getInstance();

        instance.add(gameObjectFactory.createPlayers(random.nextInt(GamePropertiesMgr.GAME_WIDTH), random.nextInt(GamePropertiesMgr.GAME_HEIGHT)));

        for (int i = 0; i < GamePropertiesMgr.INIT_ENEMY_CNT; i++) {
            instance.add(gameObjectFactory.createEnemys(random.nextInt(GamePropertiesMgr.GAME_WIDTH), random.nextInt(GamePropertiesMgr.GAME_HEIGHT)));
        }
    }

    /**
     * 添加游戏图元并添加到各自的分类列表中
     * @param object 所有游戏图元
     */
    public void add(GameObject object) {
        gameObjects.add(object);
        if (object instanceof PlayerTank) {
            players.add((PlayerTank)object);
        }

        if (object instanceof EnemyTank) {
            enemys.add((EnemyTank) object);
        }

        if (object instanceof Bullet) {
            bullets.add((Bullet) object);
        }

        if (object instanceof Explode) {
            explodes.add((Explode) object);
        }
    }

    /**
     * 移除游戏图元并分别从具体的列表中删除
     * @param object 所有游戏图元
     */
    public void remove(GameObject object) {
        gameObjects.remove(object);

        if (object instanceof PlayerTank) {
            players.remove((PlayerTank)object);
        }

        if (object instanceof EnemyTank) {
            enemys.remove((EnemyTank) object);
        }

        if (object instanceof Bullet) {
            bullets.remove((Bullet) object);
        }

        if (object instanceof Explode) {
            explodes.remove((Explode) object);
        }
    }

    private void paintGameMessage(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("当前玩家数量: " + players.size(), 40, 40);
        g.drawString("当前敌人数量: " + enemys.size(), 40, 60);

        g.drawString("当前子弹数量: " + bullets.size(), 40, 80);
        g.setColor(color);
    }

    public void paint(Graphics g) {
        for (int i = 0; i < gameObjects.size(); ++i) {
            gameObjects.get(i).paint(g);
        }

        paintGameMessage(g);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public List<Tank> getPlayers() {
        return players;
    }

    public List<Tank> getEnemys() {
        return enemys;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Explode> getExplodes() {
        return explodes;
    }
}
