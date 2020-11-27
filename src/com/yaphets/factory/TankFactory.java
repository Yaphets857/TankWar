package com.yaphets.factory;

import com.yaphets.domain.GamePoint;
import com.yaphets.enums.MoveDir;
import com.yaphets.exception.GameException;
import com.yaphets.model.EnemyTank;
import com.yaphets.model.PlayerTank;
import com.yaphets.model.Tank;
import com.yaphets.utils.GamePropertiesMgr;
import com.yaphets.utils.GameResourceMgr;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author gszqy
 * @date 16:44 2020/11/26
 * 坦克工厂: 生产玩家坦克及敌人坦克
 */
public class TankFactory {
    /**
     * 玩家列表
     */
    private List<Tank> tankPlayers = new LinkedList<>();

    /**
     * 敌人列表
     */
    private List<Tank> tankEnemys = new LinkedList<>();

    /**
     * 最大玩家数量
     */
    private static final int MAX_PLAYERS = 2;

    /**
     * 当前已存在的玩家数量
     */
    private static int playerTotal = 0;

    private static TankFactory instance = null;

    private Random random = new Random();

    private TankFactory() {

    }

    public static TankFactory getInstance() {
        if (instance == null) {
            synchronized (TankFactory.class) {
                if (instance == null) {
                    instance = new TankFactory();
                }
            }
        }
        return instance;
    }

    /**
     * x: 玩家初始x坐标
     * y: 玩家初始y坐标
     */
    public void createPlayers(int x, int y) {
        if (playerTotal >= MAX_PLAYERS) {
            throw new GameException("创建玩家数量失败，最大玩家数量" + MAX_PLAYERS + ", 当前已存在玩家数量" + playerTotal);
        }

        /*限定玩家位置在窗口内*/
        GamePoint<Integer> gamePoint = new GamePoint<>(x, y);
        checkBoundary(gamePoint);

        if (playerTotal == 0) {
            tankPlayers.add(new PlayerTank(gamePoint, MoveDir.MOVE_STOP, GameResourceMgr.player1PImageUp));
        }else if (playerTotal == 1){
            tankPlayers.add(new PlayerTank(gamePoint, MoveDir.MOVE_STOP, GameResourceMgr.player2PImageUp));
        }

        playerTotal++;
    }

    private void checkBoundary(GamePoint<Integer> gamePoint) {
        int x = Math.max(gamePoint.getX(), 2);
        int y = Math.max(gamePoint.getY(), 28);
        int imageWidth = GameResourceMgr.player1PImageUp.getWidth();
        int imageHeight = GameResourceMgr.player1PImageUp.getHeight();
        x = (x + imageWidth >= GamePropertiesMgr.GAME_WIDTH) ? (GamePropertiesMgr.GAME_WIDTH - imageWidth) : x;
        y = (y + imageHeight >= GamePropertiesMgr.GAME_HEIGHT) ? (GamePropertiesMgr.GAME_HEIGHT - imageHeight) : y;
        gamePoint.setX(x);
        gamePoint.setY(y);
    }

    /**
     * x: 敌人初始x坐标
     * y: 敌人初始y坐标
     */
    public void createEnemys(int x, int y) {

        BufferedImage image = GameResourceMgr.normalEnemyImageUp;

        /*限定玩家位置在窗口内*/
        GamePoint<Integer> gamePoint = new GamePoint<>(x, y);
        checkBoundary(gamePoint);

        //10%的概率创建strongEnemy
        if (random.nextInt(100) > 90) {
            image = GameResourceMgr.strongEnemyImageUp;
        }
        tankEnemys.add(new EnemyTank(gamePoint, MoveDir.MOVE_STOP, image));
    }

    /**
     * 获取玩家列表
     */
    public List<Tank> getPlayers() {
        return tankPlayers;
    }

    /**
     * 获取敌人列表
     */
    public List<Tank> getEnemys() {
        return tankEnemys;
    }
}
