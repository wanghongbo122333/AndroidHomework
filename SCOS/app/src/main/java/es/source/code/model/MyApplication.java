package es.source.code.model;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by WangHongbo on 2018/10/21.
 */

public class MyApplication extends Application {
    public static List<OrderItem> userOrder=new ArrayList<>();//存储用户点的菜
    public  static  List<OrderItem> billOrder=new ArrayList<>();//存储已经下单的菜
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    //查找该item,返回下标
    public static int findOrderItem(String name) {
        for (int i = 0; i < MyApplication.userOrder.size(); i++) {
            if (MyApplication.userOrder.get(i).getFood().getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public static void printItems(List<OrderItem> list) {
        for (int i = 0; i < list.size(); i++) {
            Log.d(TAG, "printItems: " +list.get(i).getFood().getName());
        }

    }
}
