package es.source.code.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;


/**
 * Created by WangHongbo on 2018/11/3.
 */
public class ServerObserverService extends Service {
    private static final String TAG = "ServerObserverService";
    private Handler cMessageHandler = new SeverHandler();
    private boolean flag = false;//SCOS是否为运行态
    private Messenger mClientMessenger;
    private MyThread myThread;
    private Messenger mServerMessenger = new Messenger(cMessageHandler);//mServerMessenger指向cMessageHandler,可以与之通信


    /**
     * SeverHandler 类,用于处理从客户端发来的消息
     */
    public class SeverHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //当传入的 Message 属性 what 值为 1 时：启动多线程模拟接收服务器传回菜品库存信息（菜名称，库存量），
                    // 每次接收到新数据
                    //判断 SCOS app 进程是否在运行状态，如果为运行状态则向
                    //SCOS app 进程发送 Message，其 what 值为 10，并在该 Message 中携带
                    //收到的库存信息（菜名称，库存量），多线程休眠频率为 300 ms
                    flag = true;//程序处于运行态
                    myThread = new MyThread(msg);
                    myThread.start();
                    break;
                case 0:
                    //当对象 cMessageHandler 使用 handleMessage()方法收到 Message
                    //属性 what 值为 0 时，则关闭模拟接收服务器传回菜品库存信息的多线程
                    myThread.stopThread();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    /**
     * MyTread类
     */
    public class MyThread extends Thread {
        //        String request = new String("name=whb&pwd=123");
//        LoginValidator loginValidator = new LoginValidator(request);
        Message msg;

        MyThread(Message msg) {
            mClientMessenger = msg.replyTo;//规定了向谁发送消息
            this.msg = msg;
            Log.d(TAG, "MyThread: Mythread");
        }

        void stopThread() {
            if (flag) {
                flag = false;
            }
        }

        @Override
        public void run() {
            Log.d(TAG, "Mythread run: ");
            if (flag) {
                Log.d(TAG, "run: flag  = 1,可以发送消息");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟上架新菜
                String foodname = "酸辣土豆丝";
                int inventory = 2;
                int price = 20;
                String type = "冷菜";
//                String msg = loginValidator.getMsg();
//                inventory = Integer.valueOf(loginValidator.getMsg());
//                System.out.println("--------------------" + msg + loginValidator.getUrl() + "------------------");

//                PackageManager packageManager = getPackageManager();
//                String appName = getApplicationInfo().loadLabel(packageManager).toString();
//                String appName = "ustc.liuhao.scos";
//                ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//                List<ActivityManager.RunningAppProcessInfo> tasksInfo = am.getRunningAppProcesses();

                //应用程序位于堆栈的顶层
//                if (appName.equals(tasksInfo.get(0).processName)) {
                    Message toClient = Message.obtain();
                    toClient.what = 10;
                    Bundle bundle = new Bundle();
                    bundle.putInt("price",price);
                    bundle.putInt("inventory", inventory);
                    bundle.putString("foodname", foodname);
                    bundle.putString("type",type);
                    toClient.setData(bundle);
                    try {
                        mClientMessenger.send(toClient);
                        Log.d(TAG, "run: service向客户端发送消息");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                }
                //                List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
//                for (ActivityManager.RunningTaskInfo info : list) {
//                    if (info.topActivity.getPackageName().equals(appName) && info.baseActivity.getPackageName().equals(appName)) {
//                        Message toClient = Message.obtain();
//                        toClient.what = 10;
//
//                        Bundle bundle = new Bundle();
//                        bundle.putInt("inventory",inventory);
//                        bundle.putString("foodname",foodname);
//                        toClient.setData(bundle);
//                        try {
//                            mClientMessenger.send(toClient);
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                        break;
//                    }
//                }
//                ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//
//                List<ActivityManager.RunningAppProcessInfo> list = activityManager.getRunningAppProcesses();
//                for (ActivityManager.RunningAppProcessInfo info : list) {
//                    if (info.processName.equals(appName)) {
//                        Message toClient = Message.obtain();
//                        toClient.what = 10;
//
//                        Bundle bundle = new Bundle();
//                        bundle.putInt("inventory",inventory);
//                        bundle.putString("foodname",foodname);
//                        toClient.setData(bundle);
//                        try {
//                            mClientMessenger.send(toClient);
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                }
            }
        }
    }
    /**
     * 重写onbind方法
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mServerMessenger.getBinder();
    }

}
