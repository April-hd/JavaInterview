package hut.c3_designpattern.strategy;

/**
 * 普通用户不打折
 */
public class GeneralStrategy implements DiscountStrategy {

    private static final String TYPE = "general";

    @Override
    public String getType() throws Exception {
        return TYPE;
    }

    @Override
    public double discount(double price) throws Exception {
        return price;
    }

}
