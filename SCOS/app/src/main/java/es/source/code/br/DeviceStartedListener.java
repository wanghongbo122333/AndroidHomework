package es.source.code.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import es.source.code.service.UpdateService;

/**
 * Created by WangHongbo on 2018/11/5.
 */
public class DeviceStartedListener extends BroadcastReceiver {
    /**
     * 当 onReceive()方法接收到设备开机广播时，启动 UpdateService 服务
     * 在 SCOS 工程的 AndroidManifest.xml 注册该广播接收器组件
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.media.AUDIO_BECOMING_NOISY") || intent.getAction().equals("Intent.ACTION_BOOT_COMPLETED")) {
            Intent service = new Intent(context, UpdateService.class);
            context.startService(service);
        }
    }
}
