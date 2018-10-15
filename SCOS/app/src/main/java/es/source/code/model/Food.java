package es.source.code.model;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class Food {
    private String name;
    private int price;
    private boolean isReturnable;//该菜可退  0不可退1可退

    public Food(String name, int price) {
        this.name = name;
        this.price = price;
        this.isReturnable = false;//所有的菜一开始都是不可退的，也就是可以点菜
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean getIsReturnable() {
            return isReturnable;
    }

    public void setIsReturnable(boolean isReturnable) {
        this.isReturnable = isReturnable;
    }
}