package es.source.code.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import es.source.code.activity.FoodDetailed;
import es.source.code.model.Food;
import es.source.code.model.HttpUtilsHttpClient;

/**
 * Created by WangHongbo on 2018/11/5.
 */
public class UpdateService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public UpdateService() {
        super("whb");
    }


    /**
     * 模拟检查服务器是否有菜品种更新信息，如有更新则使用
     * NotificationManager 发送状态栏通知，通知内容为“新品上架：菜名，价
     * 格，类型。
     * 当点击该通知时，屏幕跳转至 FoodDetailed 界面，显示该菜品详细信息
     * 在 SCOS 工程的 AndroidManifest.xml 注册该服务组件
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("HttpServer", "threadStart");
        String url = HttpUtilsHttpClient.BASE_URL + "/FoodUpdateService";

        //请求，返回json
        String result = HttpUtilsHttpClient.getRequest(url);
        if (result != null) {//如果访问服务器成功，播放系统提示音
            try {
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone rt = RingtoneManager.getRingtone(getApplicationContext(), uri);
                rt.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Food foodInfo = new Food();
        try {
            JSONObject jsonObject = new JSONObject(result);
            Log.d("httpServer", jsonObject.toString());
            foodInfo.setInventory((int) jsonObject.get("store"));
            Log.d("httpServer", "num" + foodInfo.getInventory());
        } catch (JSONException e) {
            Log.d("httpServer", "json - food - error");
        }
        //创建notification对象
        Bundle bundle = intent.getBundleExtra("info");
        final int inventory = bundle.getInt("inventory");
        final String foodName = bundle.getString("foodname");
        final int price = bundle.getInt("price");
        final String type = bundle.getString("type");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder notification = new Notification.Builder(UpdateService.this);
        notification.setTicker("新品上架")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("新品上架")
                .setContentText("菜名：" + foodName + ",价格：" + price + ",类型：" + type + "，库存：" + inventory + "")
                .setSmallIcon(android.R.drawable.ic_menu_compass)
                .setDeleteIntent(PendingIntent.getActivity(UpdateService.this, 0, new Intent(UpdateService.this, FoodDetailed.class), 0))
                .setAutoCancel(true)
                .setOngoing(false);
        notificationManager.notify(0, notification.build());

    }
}
