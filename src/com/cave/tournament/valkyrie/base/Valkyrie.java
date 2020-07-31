package com.cave.tournament.valkyrie.base;

/**
 * @description: 女武神接口（?）
 * @author: Cave
 * @create: 2020-07-30 17:04
 **/
public interface Valkyrie {

    void attackValkyrie(Valkyrie valkyrie, Integer count);

    String getName();

    Integer getHp();

    void setHp(Integer hp);

    Integer getAttack();

    void setAttack(Integer attack);

    void reduceAttack(Integer reduceAttack);

    Integer getDefense();

    void reduceDefense(Integer reduceDefense);

    void recoveryDefense();

}
