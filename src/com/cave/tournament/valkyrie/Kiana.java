package com.cave.tournament.valkyrie;

import com.cave.tournament.valkyrie.base.Valkyrie;
import com.cave.tournament.valkyrie.base.ValkyrieAttack;

import java.util.Random;

/**
 * @description: 女武神-琪亚娜
 * @author: Cave
 * @create: 2020-07-30 21:58
 **/
public class Kiana implements Valkyrie {

    private String name = "琪亚娜";

    private Integer hp = 100;

    private Integer attack = 24;

    private Integer defense = 11;

//    private Integer speed = 23;

    // true: 未眩晕, false: 眩晕
    private Boolean flag = false;

    /**
     * 吃我一矛！
     *
     * @param valkyrie 被攻击的女武神
     */
    private void skillOne(Valkyrie valkyrie) {
        // 对战 幽兰戴尔 需要触发对方 技能2
        if (valkyrie instanceof Durandal) {
            Durandal durandal = (Durandal) valkyrie;
            Boolean result = durandal.skillTwo(this);
            // 幽兰戴尔 技能2 发动成功, 则无效必杀技
            if (result) {
                return;
            }
        }

        attack = valkyrie.getDefense() * 2 + attack;
        System.out.println(name + "发动【吃我一矛！】技能，超大幅度提升攻击力，UP 后：" + attack);

        // 女武神攻击
        ValkyrieAttack.attackValkyrie(this, valkyrie);
        // 攻击结束，攻击力回复
        attack = 24;
        // 触发 技能2
        this.skillTwo();

        // 对战 罗莎莉亚 & 莉莉娅 需要触发对方 技能1
        if (valkyrie instanceof RozaliyaLiliya) {
            RozaliyaLiliya rozaliyaLiliya = (RozaliyaLiliya) valkyrie;
            rozaliyaLiliya.skillOne();
        }
    }

    /**
     * 音浪～太强～
     */
    private void skillTwo() {
        Random random = new Random();
        int successRate = random.nextInt(100) + 1;
        // 35%概率眩晕
        if (successRate <= 35) {
            flag = true;
            System.out.println(name + "触发【音浪～太强～】技能眩晕.....呜呜呜");
        }
    }

    @Override
    public void attackValkyrie(Valkyrie valkyrie, Integer count) {
        // 发起攻击前判断是否眩晕
        if (flag) {
            flag = false;
            return;
        }

        // 每两个回合发动一次 技能1
        if (count % 2 == 0) {
            this.skillOne(valkyrie);
            return;
        }

        // 女武神攻击
        ValkyrieAttack.attackValkyrie(this, valkyrie);

        // 对战 罗莎莉亚 & 莉莉娅 需要触发对方 技能1
        if (valkyrie instanceof RozaliyaLiliya) {
            RozaliyaLiliya rozaliyaLiliya = (RozaliyaLiliya) valkyrie;
            rozaliyaLiliya.skillOne();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getHp() {
        return hp;
    }

    @Override
    public void setHp(Integer hp) {
        this.hp = hp;
    }

    @Override
    public Integer getAttack() {
        return attack;
    }

    @Override
    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    @Override
    public void reduceAttack(Integer reduceAttack) {
        attack -= reduceAttack;
        if (attack < 0) {
            attack = 0;
        }
    }

    @Override
    public Integer getDefense() {
        return defense;
    }

    @Override
    public void reduceDefense(Integer reduceDefense) {
        defense -= reduceDefense;
    }

    @Override
    public void recoveryDefense() {
        defense = 11;
    }

}
