package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qweather.sdk.bean.air.AirNowBean;
import com.qweather.sdk.bean.base.Code;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.base.Unit;
import com.qweather.sdk.bean.geo.GeoBean;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.HeConfig;
import com.qweather.sdk.view.QWeather;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.logging.Level;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    TextView text,air;
    TextView tv_weather, tv_location, tv_temperature, tv_humi;
    Button btn_research,btn_back,btn_next;

    public JsonWeather.NowDTO result;
    public JsonAir.NowDTO res_air;
    public String content,content01;
    public String temp;
    public String humi, weather,icontext;
    public String location;
    public String location_content, province, city, CN_code;
    public String pubTime,aqi,primary,level,category;
    EditText ed_research;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View_init();

        ViewText_set();

        HeConfig.init("HE2207142156451394", "5d3ce3bb32d0454da285b11e8f5c4701");
        //切换至开发版服务
        HeConfig.switchToDevService();



    }
    private void View_init() {
        text=findViewById(R.id.text);
        tv_weather = (TextView) findViewById(R.id.weather);
        tv_location = (TextView) findViewById(R.id.location);
        tv_temperature = (TextView) findViewById(R.id.temp);
        tv_humi = (TextView)findViewById(R.id.humi);
        btn_research = (Button) findViewById(R.id.btn_search);
        ed_research = (EditText)findViewById(R.id.research);
        btn_back=(Button)findViewById(R.id.button_back);
        btn_next=(Button)findViewById(R.id.button_next);
        air=(TextView)findViewById(R.id.Air);


        btn_research.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }
    private void ViewText_set() {
        Bundle receive=this.getIntent().getExtras();
        String text1=receive.getString("login");
        String text2=receive.getString("password");

        text.setText("login:"+text1+"\n"+"password:"+text2);
    }





    public void getWether(String location) {
        QWeather.getWeatherNow(MainActivity.this, location, Lang.ZH_HANS, Unit.METRIC, new QWeather.OnResultWeatherNowListener() {

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "getWeather onError: " + e);
            }

            @Override
            public void onSuccess(WeatherNowBean weatherNowBean) {
                Log.i(TAG, "getWeather onSuccess: " + new Gson().toJson(weatherNowBean));
                //先判断返回的status是否正确，当status正确时获取数据，若status不正确，可查看status对应的Code值找到原因
                if (com.qweather.sdk.bean.base.Code.OK == weatherNowBean.getCode()) {
                    WeatherNowBean.NowBaseBean now = weatherNowBean.getNow();
                    content = JSON.toJSONString(weatherNowBean);
                    JsonWeather result_bean = JSONObject.parseObject(content, JsonWeather.class);

                    result = result_bean.getNow();
                    temp = result.getTemp();
                    humi = result.getHumidity();
                    weather = result.getText();
                    tv_temperature.setText(temp);
                    tv_humi.setText(humi);
                    tv_weather.setText(weather);

                    icontext=result.getIcon();

                    try {
                        LedAutoControl(icontext);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    //在此查看返回数据失败的原因
                    Code code = weatherNowBean.getCode();
                    Log.i(TAG, "failed code: " + code);
                }
            }
        });
    }

    public void getLocation(String location) {
        QWeather.getGeoCityLookup(MainActivity.this, location, new QWeather.OnResultGeoListener() {

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "getWeather onError: " + e);
                Toast.makeText(MainActivity.this, "您查询的地区不存在", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onSuccess(GeoBean geoBean) {
                //Log.i(TAG, "getWeather onSuccess: " + new Gson().toJson(geoBean));
                location_content = JSON.toJSONString(geoBean);
                Log.d(TAG, "onSuccess: ---"+location_content);


                JsonLocation result_location = JSONObject.parseObject(location_content, JsonLocation.class);
                province = result_location.getLocationBean().get(0).getAdm1();
                city = result_location.getLocationBean().get(0).getAdm2();
                tv_location.setText(province + city);
                CN_code = result_location.getLocationBean().get(0).getId();
                getWether("CN" + CN_code);

                getAir("CN" + CN_code);
                Log.d(TAG, "onSuccess: "+province+city+tv_location+CN_code);
            }
        });

    }
    public void LedAutoControl(String id) throws Exception {
        int i=Integer.parseInt(id);
        air.append("\n"+"--------------------");

        LedControl led=new LedControl();
        if(i==100){
            air.append("\n"+"灯光亮度级别:0");
            led.led_control0();
        }
        else if(i==101||i==104){
            air.append("\n"+"灯光亮度级别:3");
            led.led_control3();
        }
        else if(i==102){
            air.append("\n"+"灯光亮度级别:2");
            led.led_control2();
        }
        else if(i==103){
            air.append("\n"+"灯光亮度级别:1");
            led.led_control1();
        }
        else{
            air.append("\n"+"灯光亮度级别:3");
            led.led_control3();
        }

    }
    public void getAir(String location){
        QWeather.getAirNow(MainActivity.this,location,Lang.ZH_HANS, new QWeather.OnResultAirNowListener(){

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "getAir onError: " + e);
            }

            @Override
            public void onSuccess(AirNowBean airNowBean) {
                Log.d(TAG, "getAir onSuccess: "+new Gson().toJson(airNowBean));
                AirNowBean.NowBean now=airNowBean.getNow();
                content01=JSON.toJSONString(airNowBean);
                JsonAir result_air=JSONObject.parseObject(content01,JsonAir.class);

                res_air=result_air.getNow();
                pubTime=res_air.getPubTime();
                aqi=res_air.getAqi();
                primary=res_air.getPrimary();
                level=res_air.getLevel();
                category=res_air.getCategory();

                air.append("\n"+"数据发布时间:"+pubTime);
                air.append("\n"+"空气质量指数:"+aqi);
                air.append("\n"+"主要污染物:"+primary);
                air.append("\n"+"空气质量指数等级:"+level);
                air.append("\n"+"空气质量指数级别:"+category);


            }
        });
    }



    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_search:
                location = ed_research.getText().toString().trim();
                Log.d(TAG, "onClick: 成功输入" + location);
                Toast.makeText(this,"成功输入:"+location,Toast.LENGTH_SHORT).show();
                getLocation(location);
                air.setText("空气质量");

                break;
            case R.id.button_back:
                startActivity(new Intent(this,loginActivity.class));
                finish();
                break;
            case R.id.button_next:
                Intent intent1 = new Intent(this, IotActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }


}