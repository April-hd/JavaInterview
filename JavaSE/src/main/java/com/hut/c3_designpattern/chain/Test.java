package com.hut.c3_designpattern.chain;

/**
 * 责任链模式
 * 1、可以控制只让一个处理处理数据，第一个不满足--->交给下一个处理器，还不满足继续找，直到找到符合的处理器处理
 * 2、可以控制多个处理器处理数据，比如校验某个对象的某些数据，那么可以走多个处理器的逻辑，而不必向上面那样只走一个
 */
public class Test {

    /**
     * 通过order排序控制处理器的执行顺序
     */
    public static void doCheckChainByOrder(User user) throws Exception {
        // 创建责任链
        CheckChain<User> checkChain = new CheckChain<>();
        // 注册所有校验处理器
        checkChain.registerAllCheckHandlers();
        // 执行责任链
        checkChain.doCheckChain(user);
    }

    /**
     * 通过自己添加校验处理器，来控制走哪些校验，可以只校验年龄，也可以只校验性别，也可以都校验，如果还有顺序要求，可以加上@Order注解进行排序
     */
    public static void doCheckChainByAddHandler(User user) throws Exception {
        // 创建责任链
        CheckChain<User> checkChain = new CheckChain<>();
        // 添加要执行的校验处理器
        checkChain.addCheckHandler(new CheckGenderHandler(), new CheckAgeHandler());
        // 执行责任链
        checkChain.doCheckChain(user);
    }

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setName("戴辉");
        user.setAge(24);
        user.setGender(1);

//        doCheckChainByOrder(user);

        doCheckChainByAddHandler(user);

    }

}
