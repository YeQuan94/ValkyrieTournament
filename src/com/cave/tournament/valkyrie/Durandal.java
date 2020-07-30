package com.cave.tournament.valkyrie;

import com.cave.tournament.valkyrie.base.Valkyrie;
import com.cave.tournament.valkyrie.base.ValkyrieAttack;

import java.util.Random;

/**
 * @description: 女武神-幽兰戴尔
 * @author: Cave
 * @create: 2020-07-30 16:56
 **/
public class Durandal implements Valkyrie {

    private String name = "幽兰戴尔";

    private Integer hp = 100;

    private Integer attack = 19;

//    private Integer speed = 15;

    /**
     * 摸鱼的快乐
     */
    private void skillOne() {
        attack += 3;
        System.out.println(name + "发动【摸鱼的快乐】技能提升3点攻击力，原攻击力："
                + (attack - 3) + "，提升后攻击力：" + attack);
    }

    /**
     * 反弹！反弹无效！
     *
     * @param valkyrie 释放必杀技的女武神
     */
    Boolean skillTwo(Valkyrie valkyrie) {
        Random random = new Random();
        int successRate = random.nextInt(100) + 1;
        // 16%概率发动 技能2
        if (successRate <= 16) {
            valkyrie.setHp(valkyrie.getHp() - 30);
            System.out.println(name + "发动【反弹！反弹无效！】技能，无效对手必杀技并造成30伤害");
            return true;
        }

        System.out.println(name + "发动【反弹！反弹无效！】技能失败");
        return false;
    }

    @Override
    public void attackValkyrie(Valkyrie valkyrie, Integer count) {
        // 幽兰戴尔攻击前触发 技能1
        this.skillOne();

        // 女武神攻击
        ValkyrieAttack.attackValkyrie(attack, name, valkyrie);

        // 对战 罗莎莉亚 & 莉莉娅 需要触发对方 技能1
        if (valkyrie instanceof RozaliyaLiliya) {
            RozaliyaLiliya rozaliyaLiliya = (RozaliyaLiliya) valkyrie;
            rozaliyaLiliya.skillOne();
        }
    }

    public String getName() {
        return name;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getDefense() {
        return 10;
    }

}
