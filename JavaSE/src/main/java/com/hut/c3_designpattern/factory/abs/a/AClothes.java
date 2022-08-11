package com.hut.c3_designpattern.factory.abs.a;

import com.hut.c3_designpattern.factory.abs.Clothes;

public class AClothes implements Clothes {

    @Override
    public void make() throws Exception {
        System.out.println("这是A衣服");
    }

}
