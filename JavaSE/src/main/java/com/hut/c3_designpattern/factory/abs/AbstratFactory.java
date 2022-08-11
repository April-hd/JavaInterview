package com.hut.c3_designpattern.factory.abs;

public interface AbstratFactory {

    public Clothes getClothes() throws Exception;

    public Trousers getTrousers() throws Exception;

}
