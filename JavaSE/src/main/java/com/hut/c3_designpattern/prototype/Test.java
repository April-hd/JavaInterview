package com.hut.c3_designpattern.prototype;

public class Test {

    /**
     * 浅拷贝复制的对象，对于克隆对象里面的引用类型指向的原对象里的地址
     */
    public static void testShallowCopy() throws CloneNotSupportedException {
        User user = new User("戴辉", 24, new Location("湖南省", "武冈市", "邓家铺镇"));
        System.out.println("原对象=====>" + user);

        // 生成克隆对象
        User userClone = (User) user.clone();
        System.out.println("克隆对象===>" + userClone);

        // 更改原对象的引用变量location
        Location location = user.getLocation();
        location.setCity("邵阳市");
        location.setStreet("邵阳县");
        System.out.println("改动原对象=====>" + user);

        // 打印克隆对象，发现引用变量类型跟着变化，这是浅拷贝
        System.out.println("克隆对象跟着变===>" + userClone);
    }

    /**
     * 深拷贝复制的对象，对于克隆对象里面的引用对象会重新生成一个
     */
    public static void testDeepCopy() throws CloneNotSupportedException {
        User user = new User("戴辉", 24, new Dress("黄衣服", "灰裤子", "人字拖"));
        System.out.println("原对象=====>" + user);

        // 克隆新对象
        User userClone = (User) user.clone();
        System.out.println("克隆对象===>" + userClone);

        // 修改原对象的引用变量Dress
        Dress dress = user.getDress();
        dress.setClothes("黑衣服");
        dress.setTrousers("黑裤子");
        dress.setShoes("白鞋子");
        System.out.println("改动原对象=====>" + user);

        // 打印克隆对象
        System.out.println("克隆对象不变===>" + userClone);
    }

    public static void main(String[] args) throws CloneNotSupportedException {

//        testShallowCopy();

        testDeepCopy();

    }

}
