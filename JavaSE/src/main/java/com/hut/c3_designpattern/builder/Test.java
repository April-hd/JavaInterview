package com.hut.c3_designpattern.builder;

import com.hut.c3_designpattern.builder.builder.BWorker;
import com.hut.c3_designpattern.builder.director.*;
import com.hut.c3_designpattern.builder.entities.Product;
import com.hut.c3_designpattern.builder.entities.Product1;
import com.hut.c3_designpattern.builder.entities.Product2;
import com.hut.c3_designpattern.builder.entities.Product3;

/**
 * 构建者模式：将复杂对象的构建和表示分离，使得同样的构建过程可以有不同的表示
 */
public class Test {

    /**
     * 这种实现过程是完整的构建者模式
     * 大多数时候我们希望客户自己是指挥者，根据客户自己的需求创建产品
     * 当前方法：客户线程--->指挥者--->构建者--->产品
     */
    public static void director() throws Exception {
        Director director = new Director(); // 指挥者
        Builder builder1 = new DWorker1(); // 工人1 -> product1
        Builder builder2 = new DWorker2(); // 工人2 -> product2
        Product product1 = director.getProduct(builder1); // product1
        Product product2 = director.getProduct(builder2); // product2
        // 同样的创建过程，得到不同的表示
        // 不管工人1还是工人2，都归指挥者管理，指挥者指挥两人做同样的过程，但是得到产品对象属性不同，因为两类工人有自己的实现方式
        System.out.println(product1);
        System.out.println(product2);
    }

    /**
     * 当前用户线程就是指挥者
     * 当前线程：客户线程（充当指挥者）--->构建者--->产品
     * 与上面得区别就是可以省略Director，
     * 另一种写法可以将具体构建者作为实体类的静态内部类，实际上是省略了抽象类（Builder），只留下具体的实现工人即可
     */
    public static void builder(String A, String B, String C) throws Exception {
        BWorker bWorker = new BWorker();
        // 构建过程：A->B->C，构建最终产品得属性由客户自己控制（传输参数）(director()也可以进行参数传输，指挥者再传给构建者)
        Product product = bWorker.buildA(A).buildB(B).buildC(C).buildProduct();
        System.out.println(product);
    }

    /**
     * Product1：实体类名，为了跟上面的Product做一个区别而已，实际上等同于Product
     */
    public static void productBuilder(String A, String B, String C) {
        // 创建静态内部类Builder的实例
        Product1 product = new Product1.Builder()
                .setA(A)
                .setB(B)
                .setC(C)
                .build();
        System.out.println(product);

        // 优雅写法，在实体类里提供获取静态内部类的实例
        Product1 product1 = Product1.getBuilder()
                .setA(A)
                .setB(B)
                .setC(C)
                .build();
        System.out.println(product1);

        // 垃圾写法，虽然都可以实现，但是这种是先创建了实体类对象，再调用getBuilder方法获取Builder实例，浪费资源（白白创建一个Product1对象），多此一举
//        new Product1().getBuilder();

        // 上面采用的是Builder+链式编程，那为什么不直接改实体类的set方法，将实体类的set变为链式编程也能创建出对象，何必再创建一个静态内部类改内部类的set方法
        // 虽然改了实体类的set方法，将其变为链式编程，但这是不友好的，很多框架和工具类的底层都需要使用到原生的set方法，直接改了实体类的set方法会导致使用这些框架和工具类出现问题
        // 比如BeanUtils.copyProperties();会去找目标类的set方法，判断返回值是不是void，是的话才行，如果改成链式编程就不是void了，导致找不到set方法而无法复制属性
        // @Accessors(chain = true) // 此注解会更改原生的set方法，编程链式编程写法，具体可以看此类的class文件
        Product2 product2 = new Product2().setA(A).setB(B).setC(C);
        System.out.println(product2);

        // 除了自定义静态内部类还可以使用lombok注解，但是注意注解里的坑
        // @Builder 此注解会帮我们生成此类的静态内部类，静态内部类的build方法会使用此类的全参构造，也就是如果没有指明该类的构造方法，默认生成的是全参构造，具体可以看此类的class文件
        // 那就会导致如果需要使用无参构造的时候报错，所以最好直接定义出来无参和全参
        // 所以要用这四个必须指定一起用，不然容易出问题
        // @Data
        // @NoArgsConstructor 无参public构造方法 pubilc Product()
        // @AllArgsConstructor 全参pubilc构造方法 public Product(...) 当使用了该注解会覆盖下面的全参default构造方法
        // @Builder 全参default构造方法 Product3(...)

        // 这里之所以编译不通过，是因为生成的静态内部类的构造方法也是default的，如果测试类跟这个实体类不再同一个包下就不能使用
        // 这么一对比，是不是自定义静态内部类更灵活
        // 如果不在同一个包下也可以使用Product3.builder()，原因是@Builder还在实体类里定义了一个builder方法用来获取静态内部类的实例，这样在其他包下也能使用了
//        Product3 product3 = new Product3.Product3Builder()
//                .A("A")
//                .B("B")
//                .C("C")
//                .build();

        Product3 product3 = Product3.builder()
                .A("A")
                .B("B")
                .C("C")
                .build();
        System.out.println(product3);

    }

    /**
     * 建议自己使用静态内部类实现
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//        director();

//        builder("A", "B", "C");

        productBuilder("A", "B", "C");
    }

}
