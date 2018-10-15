package es.source.code.adapter;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class AlreadyOrderFood {
    private String name;//菜名
    private String price;//价格
    private String number;//数量
    private String remarks;//评价

    public AlreadyOrderFood(String name, String price, String number, String remarks) {
        this.name = name;
        this.price = price;
        this.number = number;
        this.remarks = remarks;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getNumber() {
        return number;
    }
    public String getRemarks() {
        return remarks;
    }
//    public String getNotorder() {
//        return "退点";
//    }
}
