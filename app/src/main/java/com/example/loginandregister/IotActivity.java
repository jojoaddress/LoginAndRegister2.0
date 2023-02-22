package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class IotActivity extends AppCompatActivity implements View.OnClickListener  {
    Button btn_begin,btn_clear,btn_ledON,btn_ledOff,btn_back,btn_tempH;
    TextView text,Temp,Humi;
    EditText led_con,tempH_text;
    ImageView img_led;

    private NotificationManager manager;
    private Notification notification;

    private int count=0;
    private float real_temp;

    private Param p1;

    private static final String TAG = "IotActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iot);
        
        //强制主线程进行网络通信
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        View_init();

        //************************************设置湿度报警通知栏**********************************//
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("terabits", "报警通知",
                    NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        //设置通知栏的格式
        notification = new NotificationCompat.Builder(this, "terabits")
                .setContentTitle("温度报警通知")
                .setContentText("请注意,温度过高！！！")
                .setSmallIcon(R.drawable.wenduji)
                .build();



        new Thread(new Runnable() {
            @Override
            public void run() {
                p1 = new Param();
                int flag=1;
                while (true) {
                    try {
                        p1.get_data();
                        Temp.setText(p1.temp + "%C");
                        Humi.setText(p1.humi + "%RH");

                        //将网络请求返回的字符串数据转为整型格式,方便进行比较
                        float real_tempH=Float.parseFloat(p1.tempH);
                        real_temp = Float.parseFloat(p1.temp);
                        Log.d(TAG, "run_alarm: "+real_tempH);
                        Log.d(TAG, "run_alarm: "+real_temp);
                        //若
                        if(real_temp>=real_tempH&&flag==1) {
                            manager.notify(1, notification);
                            Log.e(TAG, "run: 温度过高");
                            flag--;
                        }else if(real_temp<real_tempH){
                            flag=1;
                        }
                            Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
        }).start();


    }

    private void View_init() {
        btn_begin=(Button) findViewById(R.id.button_begin);
        btn_clear=(Button) findViewById(R.id.button_clear);
        btn_ledON=(Button) findViewById(R.id.led_on);
        btn_ledOff=(Button) findViewById(R.id.led_off);
        text=(TextView) findViewById(R.id.text_log);
        Temp=(TextView)findViewById(R.id.Temperature);
        Humi=(TextView)findViewById(R.id.Humidity);
        img_led=(ImageView)findViewById(R.id.led_status);
        btn_back=(Button)findViewById(R.id.button_back);
        led_con=(EditText)findViewById(R.id.led_con);
        btn_tempH=(Button)findViewById(R.id.button_tempH);
        tempH_text=(EditText)findViewById(R.id.text_tempH);

        btn_begin.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_ledON.setOnClickListener(this);
        btn_ledOff.setOnClickListener(this);
        img_led.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_tempH.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.led_on:
                img_led.setBackgroundResource(R.drawable.xiaolian);
                LedControl led01=new LedControl();
                int jud=Integer.parseInt(led_con.getText().toString());
                if(jud==3) {
                    try {
                        led01.led_control3();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(jud==2){
                    try {
                        led01.led_control2();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(jud==1){
                    try {
                        led01.led_control1();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.led_off:
                img_led.setBackgroundResource(R.drawable.hanyan);
                LedControl led02=new LedControl();
                try {
                    led02.led_control0();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.button_begin:
                count++;
                text.append("\n"+"第"+count+"次"+"\n"+p1.temp + "%C"+"\n"+p1.humi + "%RH"+"\n"+"温度阈值:"+p1.tempH+"%C");
                break;

            case R.id.button_clear:

                text.setText("");
                break;

            case R.id.button_back:
                Intent intent=new Intent();
                intent.setAction("com.example.loginandregister.MainActivity");
                Bundle bundle = new Bundle();
                bundle.putString("login", "");
                bundle.putString("password", "");
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                break;
            case R.id.button_tempH:
                TempThreshold t=new TempThreshold();
                int temph=Integer.parseInt(tempH_text.getText().toString());
                Log.d(TAG, "onClick: "+temph);
                try {
                    t.set_tempH(temph);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }

    }
}