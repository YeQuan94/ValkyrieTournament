package com.cave.tournament.valkyrie.base;

/**
 * @description: 女武神发起攻击
 * @author: Cave
 * @create: 2020-07-30 21:26
 **/
public class ValkyrieAttack {

    /**
     * 女武神攻击
     *
     * @param attack 攻击力
     * @param name 女武神名称
     * @param valkyrie 被攻击的女武神
     */
    public static void attackValkyrie(Integer attack, String name, Valkyrie valkyrie) {
        int damage = attack - valkyrie.getDefense();
        valkyrie.setHp(valkyrie.getHp() - damage);
        System.out.println(name + "攻击造成伤害：" + damage + "，" + valkyrie.getName()
                + "剩余 HP：" + valkyrie.getHp());
    }

}
