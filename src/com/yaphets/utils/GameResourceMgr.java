package com.yaphets.utils;

import com.yaphets.enums.MoveDir;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author gszqy
 * @date 17:42 2020/11/26
 */
public class GameResourceMgr {
    /**
     * 坦克相关图片
     */
    public static BufferedImage player1PImageUp;
    public static BufferedImage player2PImageUp;
    public static BufferedImage normalEnemyImageUp;
    public static BufferedImage strongEnemyImageUp;

    /**
     * 子弹相关图片
     */
    public static BufferedImage[] bulletImages;

    /**
     * 爆炸相关图片
     */
    public static BufferedImage[] explodeImages;

    static {
        ClassLoader classLoader = GameResourceMgr.class.getClassLoader();

        try {
            player1PImageUp = ImageIO.read(classLoader.getResourceAsStream("images/GoodTank1.png"));
            player2PImageUp = ImageIO.read(classLoader.getResourceAsStream("images/GoodTank2.png"));
            normalEnemyImageUp = ImageIO.read(classLoader.getResourceAsStream("images/BadTank2.png"));
            strongEnemyImageUp = ImageIO.read(classLoader.getResourceAsStream("images/BadTank1.png"));

            /*-1的原因是不包括停止*/
            int len = MoveDir.values().length - 1;
            bulletImages = new BufferedImage[MoveDir.values().length - 1];
            BufferedImage image = ImageIO.read(classLoader.getResourceAsStream("images/bulletU.png"));
            //顺时针方向依次产生MoveDir枚举类中定义的方向图片
            for (int i = 0; i < len; ++i) {
                bulletImages[i] = ImageUtil.rotateImage(image, 90 * i);
            }

            explodeImages = new BufferedImage[16];
            for (int i = 0; i < explodeImages.length; ++i) {
                explodeImages[i] = ImageIO.read(classLoader.getResourceAsStream("images/e" + (i+1) + ".gif"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private GameResourceMgr() {
    }
}
