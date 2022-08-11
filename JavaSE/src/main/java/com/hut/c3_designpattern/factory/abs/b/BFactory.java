package com.hut.c3_designpattern.factory.abs.b;

import com.hut.c3_designpattern.factory.abs.AbstratFactory;
import com.hut.c3_designpattern.factory.abs.Clothes;
import com.hut.c3_designpattern.factory.abs.Trousers;

public class BFactory implements AbstratFactory {

    @Override
    public Clothes getClothes() throws Exception {
        return new BClothes();
    }

    @Override
    public Trousers getTrousers() throws Exception {
        return new BTrousers();
    }

}
