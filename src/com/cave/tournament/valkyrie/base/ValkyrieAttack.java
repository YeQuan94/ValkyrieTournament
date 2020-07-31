package com.cave.tournament.valkyrie.base;

import com.cave.tournament.valkyrie.review.FuHua;
import com.cave.tournament.valkyrie.review.Rita;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @description: 女武神发起攻击
 * @author: Cave
 * @create: 2020-07-30 21:26
 **/
public class ValkyrieAttack {

    /**
     * 女武神攻击
     *
     * @param attackValkyrie 攻击的女武神
     * @param valkyrie 被攻击的女武神
     */
    public static void attackValkyrie(Valkyrie attackValkyrie, Valkyrie valkyrie) {
        int damage = attackValkyrie.getAttack() - valkyrie.getDefense();
        // 如果丽塔已经触发 技能2 则需要降低 60% 伤害
        damage = getRitaDamage(valkyrie, damage);
        // 最少造成1HP
        if (damage < 0) {
            damage = 1;
        }
        valkyrie.setHp(valkyrie.getHp() - damage);
        System.out.println(attackValkyrie.getName() + "攻击造成伤害：" + damage + "，" + valkyrie.getName()
                + "剩余 HP：" + valkyrie.getHp());
    }

    /**
     * 丽塔技能2降低伤害
     *
     * @param valkyrie 被攻击的女武神
     * @param damage 受到伤害值
     * @return 减少伤害值
     */
    public static int getRitaDamage(Valkyrie valkyrie, int damage) {
        if (valkyrie instanceof Rita) {
            Rita rita = (Rita) valkyrie;
            if (rita.ritaReduceDamageFlag) {
                BigDecimal bigDecimal = BigDecimal.valueOf(damage);
                int damageDown = bigDecimal.multiply(BigDecimal.valueOf(0.6)).intValue();
                System.out.println(rita.getName() + "【完美心意】技能降低 60% 伤害，原伤害：" + damage + "，降低后伤害：" + damageDown);
                damage = damageDown;
            }
        }
        return damage;
    }

    /**
     * 丽塔技能2发动
     *
     * @param valkyrie 被攻击的女武神
     * @return 是否能发动技能(true: 可以发动, false: 不能发动)
     */
    public static Boolean ritaSkillTwo(Valkyrie valkyrie) {
        if (valkyrie instanceof Rita) {
            Rita rita = (Rita) valkyrie;
            if (rita.ritaSkillTwoFlag > 0) {
                rita.ritaSkillTwoFlag --;
                System.out.println(rita.getName() + "【完美心意】技能触发，剩余回合数：" + rita.ritaSkillTwoFlag);
                return false;
            }
        }
        return true;
    }

    public static Boolean fuHuaSkillTwo(Valkyrie valkyrie) {
        if (valkyrie instanceof FuHua) {
            FuHua fuHua = (FuHua) valkyrie;
            Random random = new Random();
            int successRate = random.nextInt(100) + 1;
            if (successRate < fuHua.missRate) {
                System.out.println(valkyrie.getName() + "成功闪避攻击，MISS Rate："
                        + fuHua.missRate + "，随机数：" + successRate);
                return true;
            }
        }

        return false;
    }

}
