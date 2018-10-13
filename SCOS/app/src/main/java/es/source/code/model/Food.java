package es.source.code.model;

/**
 * Created by Wanghongbo on 2018/10/10.
 */

public class Food {
    private String foodName;
    private String foodPrice;

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public String toString() {
        return "foodName" + foodName + "foodPrice" + foodPrice;
    }
}
