package com.hut.c3_designpattern.factory.abs.a;

import com.hut.c3_designpattern.factory.abs.AbstratFactory;
import com.hut.c3_designpattern.factory.abs.Clothes;
import com.hut.c3_designpattern.factory.abs.Trousers;

public class AFactory implements AbstratFactory {

    @Override
    public Clothes getClothes() throws Exception {
        return new AClothes();
    }

    @Override
    public Trousers getTrousers() throws Exception {
        return new ATrousers();
    }

}
