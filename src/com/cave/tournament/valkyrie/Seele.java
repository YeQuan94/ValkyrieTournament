package com.cave.tournament.valkyrie;

import com.cave.tournament.valkyrie.base.Valkyrie;
import com.cave.tournament.valkyrie.base.ValkyrieAttack;

import java.util.Random;

/**
 * @description: 女武神-希儿
 * @author: Cave
 * @create: 2020-07-30 17:27
 **/
public class Seele implements Valkyrie {

    private String name = "希儿";

    private Integer hp = 100;

    private Integer attack = 23;

    private Integer defense = 13;

    private Integer speed = 26;

    // true: 白希, false: 黑希
    private Boolean flag = true;

    /**
     * 我 换 我 自 己
     */
    private void skillOne() {
        // ......
        this.skillTwo();
    }

    /**
     * 拜托了另一个我
     */
    private void skillTwo() {
        if (flag) {
            attack += 10;
            defense -= 5;
            flag = false;
            System.out.println("白希变黑希，攻击力+10，防御力-5");
        } else {
            attack -= 10;
            defense += 5;
            Random random = new Random();
            // 随机回复1-15 HP
            int hpReply = random.nextInt(15) + 1;
            hp += hpReply;
            flag = true;
            System.out.println("黑希变白希，攻击力-10，防御力+5，HP +" + hpReply);
        }

        if (hp > 100) {
            System.out.println("黑希变白希回复 HP，HP 总量大于 100，HP：" + hp);
            hp = 100;
        }
    }

    @Override
    public void attackValkyrie(Valkyrie valkyrie, Integer count) {
        // 希儿攻击前触发 技能1 和 技能2
        this.skillOne();

        // 女武神攻击
        ValkyrieAttack.attackValkyrie(attack, name, valkyrie);
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
    public Integer getDefense() {
        return defense;
    }

}
