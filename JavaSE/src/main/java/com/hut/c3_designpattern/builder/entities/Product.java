package com.hut.c3_designpattern.builder.entities;

import java.util.Objects;

/**
 * 产品
 */
public class Product {

    private String A; // A属性

    private String B; // B属性

    private String C; // C属性

    public Product() {
    }

    public Product(String a, String b, String c) {
        A = a;
        B = b;
        C = c;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(A, product.A) && Objects.equals(B, product.B) && Objects.equals(C, product.C);
    }

    @Override
    public int hashCode() {
        return Objects.hash(A, B, C);
    }

    @Override
    public String toString() {
        return "Product{" +
                "A='" + A + '\'' +
                ", B='" + B + '\'' +
                ", C='" + C + '\'' +
                '}';
    }

}
