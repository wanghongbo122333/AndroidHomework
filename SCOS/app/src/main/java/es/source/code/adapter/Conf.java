package es.source.code.adapter;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class Conf {
    private String name;
    private String price;
    private String choose;//菜品后面的按钮的值

    public Conf(String name, String price, String choose) {
        this.name = name;
        this.price = price;
        this.choose = choose;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getChoose() {
        return choose;
    }
    public void setChoose(String choose){
        this.choose = choose;
    }
}