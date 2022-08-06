package hut.c3_designpattern.prototype;

/**
 * 用户穿着
 */
public class Dress implements Cloneable {

    private String clothes; // 衣服

    private String trousers; // 裤子

    private String shoes; // 鞋子

    public Dress() {
    }

    public Dress(String clothes, String trousers, String shoes) {
        this.clothes = clothes;
        this.trousers = trousers;
        this.shoes = shoes;
    }

    public String getClothes() {
        return clothes;
    }

    public void setClothes(String clothes) {
        this.clothes = clothes;
    }

    public String getTrousers() {
        return trousers;
    }

    public void setTrousers(String trousers) {
        this.trousers = trousers;
    }

    public String getShoes() {
        return shoes;
    }

    public void setShoes(String shoes) {
        this.shoes = shoes;
    }

    @Override
    public String toString() {
        return "Dress{" +
                "clothes='" + clothes + '\'' +
                ", trousers='" + trousers + '\'' +
                ", shoes='" + shoes + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
