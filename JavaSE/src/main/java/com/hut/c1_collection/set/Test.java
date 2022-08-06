package com.hut.c1_collection.set;

import java.util.HashSet;

public class Test {

    public static void testHashSet() {

        HashSet<String> set = new HashSet<>();
        set.add("1");
        set.add("1");
        set.add("a");
        set.add("f");
        set.add("c");
        System.out.println(set);

    }

    public static void testTreeSet() {

    }

    public static void main(String[] args) {

        testHashSet();

    }

}
