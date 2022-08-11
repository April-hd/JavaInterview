package com.hut.c3_designpattern.factory.simple;

import com.hut.c3_designpattern.factory.abs.AbstratFactory;
import com.hut.c3_designpattern.factory.abs.Clothes;
import com.hut.c3_designpattern.factory.abs.Trousers;
import com.hut.c3_designpattern.factory.abs.a.AFactory;
import com.hut.c3_designpattern.factory.abs.b.BFactory;

public class Test {

    /**
     * 简单工厂模式
     */
    public static void testSimpleFactory() {
        SimpleFactory simpleFactory = new SimpleFactory();
        SimpleProduct simpleProduct = simpleFactory.getProduct();
        System.out.println(simpleProduct);
    }

    /**
     * 抽象工厂模式
     * 很方便就能切换工厂
     */
    public static void testAbstractFacory() throws Exception {
        AbstratFactory abstratFactory = new AFactory();
//        AbstratFactory abstratFactory = new BFactory();
        Clothes clothes = abstratFactory.getClothes();
        Trousers trousers = abstratFactory.getTrousers();
        clothes.make();
        trousers.make();
    }

    public static void main(String[] args) throws Exception {

        testSimpleFactory();

        testAbstractFacory();

    }

}
