package com.hut.c3_designpattern.factory.abs.b;

import com.hut.c3_designpattern.factory.abs.Trousers;

public class BTrousers implements Trousers {

    @Override
    public void make() throws Exception {
        System.out.println("这是B裤子");
    }

}
