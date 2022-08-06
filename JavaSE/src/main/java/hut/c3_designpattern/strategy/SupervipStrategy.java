package hut.c3_designpattern.strategy;

/**
 * supervip客户策略
 */
public class SupervipStrategy implements DiscountStrategy {

    private static final String TYPE = "supervip";

    @Override
    public String getType() throws Exception {
        return TYPE;
    }

    @Override
    public double discount(double price) throws Exception {
        return price * 0.8;
    }

}
