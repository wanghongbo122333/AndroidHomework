package es.source.code.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SCOSHelper extends AppCompatActivity {
    private List<Map<String, Object>> data_list;
    private int[] icon = {R.drawable.agreement, R.drawable.system, R.drawable.manual, R.drawable.message, R.drawable.email};
    private String[] iconName = {"用户使用协议", "关于系统", "电话人工帮助", "短信帮助", "邮件帮助"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scos_helper);

        GridView gridView = findViewById(R.id.help_gridView);
        String[] from = {"image", "name"};
        int[] to = {R.id.image, R.id.gridname};
        data_list = new ArrayList<>();
        initial();
        SimpleAdapter adapter = new SimpleAdapter(this, data_list, R.layout.main_item, from, to);
        //配置适配器
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://用户使用协议
                        Toast.makeText(getApplicationContext(), "使用协议", Toast.LENGTH_SHORT).show();
                        break;
                    case 1://关于系统
                        Toast.makeText(getApplicationContext(), "关于系统", Toast.LENGTH_SHORT).show();
                        break;
                    case 2://电话人工帮助
                        Toast.makeText(getApplicationContext(), "电话帮助", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "5554"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                    case 3://短信帮助
                        Toast.makeText(getApplicationContext(), "短信帮助", Toast.LENGTH_SHORT).show();
                        intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:5554"));
                        intent.putExtra("sms_body", "test scos helper");
                        startActivity(intent);
                        break;
                    case 4://邮件帮助
                        Toast.makeText(getApplicationContext(), "邮件帮助", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
    }

    /**
     * 初始化菜单项
     */
    public void initial() {
        for (int j = 0; j < 5; j++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[j]);
            map.put("name", iconName[j]);
            this.data_list.add(map);
        }
    }
}
