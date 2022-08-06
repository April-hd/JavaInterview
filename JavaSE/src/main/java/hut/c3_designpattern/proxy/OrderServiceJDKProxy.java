package hut.c3_designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理要求被代理类必须有实现的接口
 * 可以对被代理类实现的接口里的方法进行增强
 */
public class OrderServiceJDKProxy {

    private OrderService orderService;

    public OrderServiceJDKProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 返回的代理对象是被代理类的接口的另一个实现类，所以得转换为被代理类得接口类型（多态），所以代理对象只能对接口里定义的方法进行增强，其他不在接口里的方法不能增强
     * 生成得代理类在内存里
     * @return
     */
    public OrderInterface getOrderServiceJDKProxyInstance() {
        return (OrderInterface) Proxy.newProxyInstance(
                orderService.getClass().getClassLoader(),
                orderService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开启事务");
                        try {
                            method.invoke(orderService);
                            System.out.println("提交事务");
                        } catch (Exception e) {
                            System.out.println("事务回滚");
                            e.printStackTrace();
                        }
                        return null;
                    }
                });
    }

}
