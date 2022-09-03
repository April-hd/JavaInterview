package com.hut.c3_designpattern.builder.entities;

import java.util.Objects;

/**
 * 更改原生的set方法
 */
public class Product2 {

    private String A;

    private String B;

    private String C;

    public Product2() {
    }

    public String getA() {
        return A;
    }

    public Product2 setA(String a) {
        A = a;
        return this;
    }

    public String getB() {
        return B;
    }

    public Product2 setB(String b) {
        B = b;
        return this;
    }

    public String getC() {
        return C;
    }

    public Product2 setC(String c) {
        C = c;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product2 product2 = (Product2) o;
        return Objects.equals(A, product2.A) && Objects.equals(B, product2.B) && Objects.equals(C, product2.C);
    }

    @Override
    public int hashCode() {
        return Objects.hash(A, B, C);
    }

    @Override
    public String toString() {
        return "Product2{" +
                "A='" + A + '\'' +
                ", B='" + B + '\'' +
                ", C='" + C + '\'' +
                '}';
    }

}
