package hut.c3_designpattern.strategy;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

/**
 * 会员打折
 * 普通用户不打折
 * vip用户 9折
 * supervip用户 8折
 */
public class OrderService {

    /**
     * 根据开闭原则，这种方法不便于扩展，每当有新的客户类型，就需要改动下面的代码
     */
    public double discount(String type, double price) {
        if ("vip".equals(type)) {
            return price * 0.9;
        } else if ("supervip".equals(type)) {
            return price * 0.8;
        }
        return price;
    }

    /**
     * 存放所有策略
     */
    HashMap<String, DiscountStrategy> map = new HashMap<>();

    /**
     * 构造方法初始化所有策略
     */
    public OrderService() {
        Reflections reflections = new Reflections();
        // 反射获取策略接口的所有实现类并存放进map集合里
        Set<Class<? extends DiscountStrategy>> subTypesOfDiscountStrategy = reflections.getSubTypesOf(DiscountStrategy.class);
        subTypesOfDiscountStrategy.stream().forEach(c -> {
            try {
                DiscountStrategy discountStrategy = c.getDeclaredConstructor().newInstance();
                map.put(discountStrategy.getType(), discountStrategy);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 每有一种新策略，我就扔到map里，不用改动原有代码，只需要去关注每一种策略的实现就可以了，便于扩展
     */
    public double discountByStrategy(String type, double price) throws Exception {
        return map.get(type).discount(price);
    }

}
