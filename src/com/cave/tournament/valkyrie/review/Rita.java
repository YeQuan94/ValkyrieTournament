package com.cave.tournament.valkyrie.review;

import com.cave.tournament.valkyrie.base.Valkyrie;
import com.cave.tournament.valkyrie.base.ValkyrieAttack;

import java.util.Random;

/**
 * @description: 女武神-丽塔
 * @author: Cave
 * @create: 2020-07-31 09:44
 **/
public class Rita implements Valkyrie {

    private String name = "丽塔";

    private Integer hp = 100;

    private Integer attack = 26;

    private Integer defense = 11;

//    private Integer speed = 17;

    // 丽塔 技能2 标示，不能使用技能的回合数
    public Integer ritaSkillTwoFlag = 0;

    // 丽塔 技能2 减少伤害标示，降低 60% 伤害
    public Boolean ritaReduceDamageFlag = false;

    /**
     * 女仆的温柔清理
     *
     * @param valkyrie 被攻击的女武神
     */
    private void skillOne(Valkyrie valkyrie) {
        Random random = new Random();
        int successRate = random.nextInt(100) + 1;
        // 35%概率发动 技能1
        if (successRate <= 35) {
            attack -= 3;
            valkyrie.reduceAttack(4);
            System.out.println(name + "发动【女仆的温柔清理】技能，本次攻击降低3点，对手永久下降4点攻击力");
        }
    }

    /**
     * 完美心意
     *
     * @param valkyrie 被攻击的女武神
     */
    private void skillTwo(Valkyrie valkyrie) {
        valkyrie.setHp(valkyrie.getHp() + 4);
        // 两回合不能使用技能
        ritaSkillTwoFlag = 2;
        // 造成伤害降低60%，（攻击力 - 防御力） * 0.6
        ritaReduceDamageFlag = true;
        System.out.println(name + "发动【完美心意】技能，回复对手4HP，两回合不能使用技能，造成伤害永久下降60%");
    }

    @Override
    public void attackValkyrie(Valkyrie valkyrie, Integer count) {
        // 每四个回合发动一次 技能2
        if (count % 4 == 0) {
            this.skillTwo(valkyrie);
            return;
        }

        // 丽塔攻击前触发 技能1
        this.skillOne(valkyrie);

        if (!ValkyrieAttack.fuHuaSkillTwo(valkyrie)) {
            // 女武神攻击
            ValkyrieAttack.attackValkyrie(this, valkyrie);
        }

        // 攻击力复原
        attack = 26;
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
