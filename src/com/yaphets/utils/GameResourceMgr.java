package com.yaphets.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author gszqy
 * @date 17:42 2020/11/26
 *
 */
public class GameResourceMgr {
    public static BufferedImage player1PImage;
    public static BufferedImage player2PImage;
    public static BufferedImage normalEnemyImage;
    public static BufferedImage strongEnemyImage;


    static {
        ClassLoader classLoader = GameResourceMgr.class.getClassLoader();

        try {
            player1PImage = ImageIO.read(classLoader.getResourceAsStream("images/GoodTank1.png"));
            player2PImage = ImageIO.read(classLoader.getResourceAsStream("images/GoodTank2.png"));
            normalEnemyImage = ImageIO.read(classLoader.getResourceAsStream("images/BadTank2.png"));
            strongEnemyImage = ImageIO.read(classLoader.getResourceAsStream("images/BadTank1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private GameResourceMgr() {
    }
}
