package com.yaphets.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author gszqy
 * @date 17:42 2020/11/26
 */
public class GameResourceMgr {
    public static BufferedImage player1PImageUp;
    public static BufferedImage player2PImageUp;
    public static BufferedImage normalEnemyImageUp;
    public static BufferedImage strongEnemyImageUp;


    static {
        ClassLoader classLoader = GameResourceMgr.class.getClassLoader();

        try {
            player1PImageUp = ImageIO.read(classLoader.getResourceAsStream("images/GoodTank1.png"));
            player2PImageUp = ImageIO.read(classLoader.getResourceAsStream("images/GoodTank2.png"));
            normalEnemyImageUp = ImageIO.read(classLoader.getResourceAsStream("images/BadTank2.png"));
            strongEnemyImageUp = ImageIO.read(classLoader.getResourceAsStream("images/BadTank1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private GameResourceMgr() {
    }
}
