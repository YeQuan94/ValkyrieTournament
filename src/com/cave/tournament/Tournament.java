package com.cave.tournament;

import com.cave.tournament.valkyrie.*;
import com.cave.tournament.valkyrie.base.Valkyrie;

/**
 * @description: 武斗会
 * @author: Cave
 * @create: 2020-07-30 16:53
 **/
public class Tournament {

    private static Integer valkyrieACount = 0;

    private static Integer valkyrieBCount = 0;

    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {
            kianaVSRozaliyaLiliya();
        }

        System.out.println("琪亚娜胜利次数：" + valkyrieACount + "，阿琳姐妹胜利次数：" + valkyrieBCount);

    }

    /**
     * 琪亚娜 VS 罗莎莉亚 & 莉莉娅
     */
    private static void kianaVSRozaliyaLiliya() {
        int count = 1;

        // 琪亚娜速度快于阿琳姐妹，A：琪亚娜，B：阿琳姐妹
        Valkyrie valkyrieA = new Kiana();
        Valkyrie valkyrieB = new RozaliyaLiliya();

        tournament(count, valkyrieA, valkyrieB);
    }

    /**
     * 琪亚娜 VS 幽兰戴尔
     */
    private static void kianaVSDurandal() {
        int count = 1;

        // 琪亚娜速度快于幽兰戴尔，A：琪亚娜，B：幽兰戴尔
        Valkyrie valkyrieA = new Kiana();
        Valkyrie valkyrieB = new Durandal();

        tournament(count, valkyrieA, valkyrieB);
    }

    /**
     * 幽兰戴尔 VS 罗莎莉亚 & 莉莉娅
     */
    private static void durandalVSRozaliyaLiliya() {
        int count = 1;

        // 幽兰戴尔速度快于阿琳姐妹，A：幽兰戴尔，B：阿琳姐妹
        Valkyrie valkyrieA = new Durandal();
        Valkyrie valkyrieB = new RozaliyaLiliya();

        tournament(count, valkyrieA, valkyrieB);
    }

    /**
     * 希儿 VS 樱莲
     */
    private static void seeleVSSakuraKallen() {
        int count = 1;

        // 希儿速度快于樱莲，A：希儿，B：樱莲
        Valkyrie valkyrieA = new Seele();
        Valkyrie valkyrieB = new SakuraKallen();

        tournament(count, valkyrieA, valkyrieB);
    }

    /**
     * 女武神武斗
     *
     * @param count 回合次数
     * @param valkyrieA 速度快的女武神
     * @param valkyrieB 速度慢的女武神
     */
    private static void tournament(int count, Valkyrie valkyrieA, Valkyrie valkyrieB) {
        while (valkyrieA.getHp() > 0 && valkyrieB.getHp() > 0) {
            System.out.println("第 " + count + " 回合开始");

            valkyrieA.attackValkyrie(valkyrieB, count);
            if (valkyrieB.getHp() <= 0) {
                break;
            }

            valkyrieB.attackValkyrie(valkyrieA, count);

            count++;
        }

        System.out.println(valkyrieA.getName() + "剩余 HP: " + valkyrieA.getHp() + ", " + valkyrieB.getName() + "剩余 HP: " + valkyrieB.getHp());

        if (valkyrieA.getHp() > 0) {
            valkyrieACount += 1;
        } else {
            valkyrieBCount += 1;
        }
    }

}
