package hut.c3_designpattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib动态代理要求被代理类不能是final，因为Cglib动态代理类是要继承被代理类的
 * 可以对被代理类的所有方法进行一个增强
 */
public class OrderServiceCglibProxy implements MethodInterceptor {

    private OrderService orderService;

    public OrderServiceCglibProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
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

    public OrderService getOrderServiceCglibProxyInstance() {
        // Enhancer类作为被代理类的子类
        Enhancer enhancer = new Enhancer();
        // 设置父类为被代理类
        enhancer.setSuperclass(orderService.getClass());
        // 设置enhancer的回调方法为本类的重写方法intercept
        enhancer.setCallback(this);
        OrderService orderServiceCglibProxy = (OrderService) enhancer.create();
        return orderServiceCglibProxy;
    }

}
