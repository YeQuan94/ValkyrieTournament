package com.cave.tournament.valkyrie;

import com.cave.tournament.valkyrie.base.Valkyrie;
import com.cave.tournament.valkyrie.base.ValkyrieAttack;

import java.util.Random;

/**
 * @description: 女武神-八重樱 & 卡莲
 * @author: Cave
 * @create: 2020-07-30 17:28
 **/
public class SakuraKallen implements Valkyrie {

    private String name = "樱莲";

    private Integer hp = 100;

    private Integer attack = 20;

    private Integer defense = 9;

//    private Integer speed = 18;

    /**
     * 八重樱的饭团
     */
    private void skillOne() {
        Random random = new Random();
        int successRate = random.nextInt(100) + 1;
        // 30%概率发动技能1
        if (successRate <= 30) {
            hp += 25;
            System.err.println(name + "30%概率发动【八重樱的饭团】技能，回复25 HP，回复结果：" + hp);
            if (hp > 100) {
                System.err.println(name + "发动技能回复 HP，HP 总量大于 100，HP：" + hp);
                hp = 100;
            }
        }
    }

    /**
     * 卡莲的饭团
     *
     * @param valkyrie 被攻击的女武神
     */
    private void skillTwo(Valkyrie valkyrie) {
        valkyrie.setHp(valkyrie.getHp() - 25);
        System.out.println(name + "发动【卡莲的饭团】技能造成 25 伤害，" + valkyrie.getName() + "剩余 HP：" + valkyrie.getHp());
    }

    @Override
    public void attackValkyrie(Valkyrie valkyrie, Integer count) {
        // 樱莲攻击前触发 技能1
        this.skillOne();

        // 每两个回合发动一次 技能2
        if (count % 2 == 0) {
            this.skillTwo(valkyrie);
            return;
        }

        // 女武神攻击
        ValkyrieAttack.attackValkyrie(this, valkyrie);
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
        defense = 9;
    }

}
