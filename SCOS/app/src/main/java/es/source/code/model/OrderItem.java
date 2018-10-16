package es.source.code.model;

/**
 * Created by WangHongbo on 2018/10/16.
 */
//菜单项
public class OrderItem {
    private  Food food  ;//一个菜的对象
    private int amount;//数量
    private  String remarks;//备注

    public OrderItem(Food food, int amount, String remarks) {
        this.food = food;
        this.amount = amount;
        this.remarks = remarks;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Food getFood() {
        return food;
    }

    public int getAmount() {
        return amount;
    }

    public String getRemarks() {
        return remarks;
    }
}
