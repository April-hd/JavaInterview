package com.hut.c3_designpattern.factory.abs.b;

import com.hut.c3_designpattern.factory.abs.Clothes;

public class BClothes implements Clothes {

    @Override
    public void make() throws Exception {
        System.out.println("这是B衣服");
    }

}
