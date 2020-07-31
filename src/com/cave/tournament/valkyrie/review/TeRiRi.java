package com.cave.tournament.valkyrie.review;

import com.cave.tournament.valkyrie.base.Valkyrie;
import com.cave.tournament.valkyrie.base.ValkyrieAttack;

import java.util.Random;

/**
 * @description: 女武神-德丽莎
 * @author: Cave
 * @create: 2020-07-31 10:07
 **/
public class TeRiRi implements Valkyrie {

    private String name = "德丽莎";

    private Integer hp = 100;

    private Integer attack = 19;

    private Integer defense = 12;

//    private Integer speed = 22;

    /**
     * 血犹大第一可爱
     *
     * @param valkyrie 被攻击的女武神
     */
    private void skillOne(Valkyrie valkyrie) {
        Random random = new Random();
        int successRate = random.nextInt(100) + 1;
        // 30%概率发动 技能1
        if (successRate <= 30) {
            // 降低5点防御，永久还是一回合，没有描述清楚
            // 现阶段是永久
            valkyrie.reduceDefense(5);
            System.out.println(name + "发动【血犹大第一可爱】技能，降低5点防御力");
        }
    }

    /**
     * 在线踢人
     *
     * @param valkyrie 被攻击的女武神
     */
    private void skillTwo(Valkyrie valkyrie) {
        if (ValkyrieAttack.fuHuaSkillTwo(valkyrie)) {
            return;
        }

        Valkyrie teRiRi = new TeRiRi();
        teRiRi.setAttack(16);
        // 造成5次16点伤害
        for (int i = 0; i < 5; i++) {
            // 女武神攻击
            ValkyrieAttack.attackValkyrie(teRiRi, valkyrie);
        }
        System.out.println(name + "发动【在线踢人】技能，造成5次16点伤害，不无视防御力");
    }

    @Override
    public void attackValkyrie(Valkyrie valkyrie, Integer count) {
        // 对战 丽塔 需要触发对方 技能2
        Boolean result = ValkyrieAttack.ritaSkillTwo(valkyrie);

        // 每三个回合发动一次 技能2
        if (count % 3 == 0 && result) {
            this.skillTwo(valkyrie);

            // 对手女武神防御力复原
//            valkyrie.recoveryDefense();
            return;
        }

        if (!ValkyrieAttack.fuHuaSkillTwo(valkyrie)) {
            // 女武神攻击
            ValkyrieAttack.attackValkyrie(this, valkyrie);
        }

        // 对手女武神防御力复原
//        valkyrie.recoveryDefense();

        // 德丽莎攻击后触发 技能1
        if (result) {
            this.skillOne(valkyrie);
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
        defense = 12;
    }

}
