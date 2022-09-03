package com.hut.c3_designpattern.builder.entities;

import java.util.Objects;

public class Product1 {

    private String A;

    private String B;

    private String C;

    public Product1() {
    }

    public Product1(String a, String b, String c) {
        A = a;
        B = b;
        C = c;
    }

    // 提供一个获取静态内部类Builder的实例方法
    public static Product1.Builder getBuilder() {
        return new Product1.Builder();
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

    public static class Builder {
        private String A;

        private String B;

        private String C;

        public Builder() {
        }

        public Builder setA(String a) {
            A = a;
            return this;
        }

        public Builder setB(String b) {
            B = b;
            return this;
        }

        public Builder setC(String c) {
            C = c;
            return this;
        }

        public Product1 build() {
            return new Product1(A, B, C);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product1 that = (Product1) o;
        return Objects.equals(A, that.A) && Objects.equals(B, that.B) && Objects.equals(C, that.C);
    }

    @Override
    public int hashCode() {
        return Objects.hash(A, B, C);
    }

    @Override
    public String toString() {
        return "Product1{" +
                "A='" + A + '\'' +
                ", B='" + B + '\'' +
                ", C='" + C + '\'' +
                '}';
    }

}
