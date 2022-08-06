package com.hut.c3_designpattern.prototype;

/**
 * 用户类
 */
public class User implements Cloneable {

    private String nmae; // 姓名

    private Integer age; // 年龄

    private Location location; // 住址

    private Dress dress; // 穿着

    public User() {
    }

    public User(String nmae, Integer age, Location location) {
        this.nmae = nmae;
        this.age = age;
        this.location = location;
    }

    public User(String nmae, Integer age, Dress dress) {
        this.nmae = nmae;
        this.age = age;
        this.dress = dress;
    }

    public String getNmae() {
        return nmae;
    }

    public void setNmae(String nmae) {
        this.nmae = nmae;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Dress getDress() {
        return dress;
    }

    public void setDress(Dress dress) {
        this.dress = dress;
    }

    @Override
    public String toString() {
        return "User{" +
                "nmae='" + nmae + '\'' +
                ", age=" + age +
                ", location=" + location +
                ", dress=" + dress +
                '}';
    }

    /**
     * String有字符串常量池，所以当更改原对象的String类型数据后，不会影响原String的内存地址，原对象的String指向另一块内存地址，所以String类型改变不会影响克隆类型
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 先客隆原对象
        User userClone = (User) super.clone();
        // 再克隆原对象里的引用对象，然后放入克隆对象里，这是深拷贝
        userClone.setDress((Dress) userClone.dress.clone());
        return userClone;
    }

}