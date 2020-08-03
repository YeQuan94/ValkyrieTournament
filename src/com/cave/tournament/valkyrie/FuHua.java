package com.cave.tournament.valkyrie;

import com.cave.tournament.valkyrie.base.Valkyrie;
import com.cave.tournament.valkyrie.base.ValkyrieAttack;

/**
 * @description: 女武神-符华
 * @author: Cave
 * @create: 2020-07-31 11:40
 **/
public class FuHua implements Valkyrie {

    private String name = "符华";

    private Integer hp = 100;

    private Integer attack = 17;

    private Integer defense = 15;

//    private Integer speed = 16;

    // MISS 概率
    public Integer missRate = 0;

    /**
     * 凭神化剑
     *
     * @param valkyrie 被攻击的女武神
     */
    private void skillOne(Valkyrie valkyrie) {
        int ritaDamage = ValkyrieAttack.getRitaDamage(valkyrie, attack);
        valkyrie.setHp(valkyrie.getHp() - ritaDamage);
        System.out.println(name + "发动【凭神化剑】技能，直接造成伤害：" + attack + "，"
                + valkyrie.getName() + "剩余 HP：" + valkyrie.getHp());
    }

    /**
     * 形之笔墨
     *
     * @param valkyrie 被攻击的女武神
     */
    private void skillTwo(Valkyrie valkyrie) {
        System.out.println(name + "发动【形之笔墨】技能，造成18点伤害，并且增加25闪避");
        Valkyrie fuhua = new FuHua();
        fuhua.setAttack(18);
        ValkyrieAttack.attackValkyrie(fuhua, valkyrie);
        if (missRate == 0) {
            missRate = 25;
        } else {
            missRate = missRate + (missRate * 25 / 100);
        }
    }

    @Override
    public void attackValkyrie(Valkyrie valkyrie, Integer count) {
        // 对战 丽塔 需要触发对方 技能2
        Boolean result = ValkyrieAttack.ritaSkillTwo(valkyrie);

        // 每三个回合发动一次 技能2
        if (count % 3 == 0 && result) {
            this.skillTwo(valkyrie);
            return;
        }

        // 无视防御攻击
        this.skillOne(valkyrie);
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
        defense = 15;
    }

}
