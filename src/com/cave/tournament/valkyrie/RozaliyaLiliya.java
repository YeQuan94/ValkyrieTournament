package com.cave.tournament.valkyrie;

import com.cave.tournament.valkyrie.base.Valkyrie;
import com.cave.tournament.valkyrie.base.ValkyrieAttack;

import java.util.Random;

/**
 * @description: 女武神-罗莎莉亚 & 莉莉娅
 * @author: Cave
 * @create: 2020-07-30 17:11
 **/
public class RozaliyaLiliya implements Valkyrie {

    private String name = "阿琳姐妹";

    private Integer hp = 100;

    private Integer attack = 18;

//    private Integer speed = 10;

    // true: 未受到过致命攻击, false: 已受到过致命攻击
    private Boolean skillOneFlag = true;

    // true: 发动技能2, false: 不发动技能2
    private Boolean skillTwoFlag = false;

    /**
     * 96度生命之水
     */
    void skillOne() {
        // 第一次受到致命攻击时发动
        if (skillOneFlag && hp <= 0) {
            hp = 20;
            System.out.println(name + "第一次受到致命攻击发动【96度生命之水】技能，HP 回复至20");

            // 只能发动一次
            skillOneFlag = false;

            // 触发 技能2
            skillTwoFlag = true;
        }
    }

    /**
     * 变成星星吧！
     * PS：按照描述来看，这里并不是无视防御
     *
     * @param valkyrie 被攻击的女武神
     */
    private void skillTwo(Valkyrie valkyrie) {
        // 对战 幽兰戴尔 需要触发对方 技能2
        if (valkyrie instanceof Durandal) {
            Durandal durandal = (Durandal) valkyrie;
            Boolean result = durandal.skillTwo(this);
            // 幽兰戴尔 技能2 发动成功, 则无效必杀技
            if (result) {
                return;
            }
        }

        Random random = new Random();
        int successRate = random.nextInt(100) + 1;
        // 50%概率发动 技能2 造成233伤害
        if (successRate <= 50) {
            System.out.println(name + "触发【变成星星吧！】技能造成233大伤害");
            ValkyrieAttack.attackValkyrie(233, name, valkyrie);

        } else {
            System.out.println(name + "触发【变成星星吧！】技能造成50伤害");
            ValkyrieAttack.attackValkyrie(50, name, valkyrie);
        }

        // 只能发动一次
        skillTwoFlag = false;
    }

    @Override
    public void attackValkyrie(Valkyrie valkyrie, Integer count) {
        // 满足 技能2 触发条件
        if (skillTwoFlag) {
            this.skillTwo(valkyrie);
            return;
        }

        // 女武神攻击
        ValkyrieAttack.attackValkyrie(attack, name, valkyrie);
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
