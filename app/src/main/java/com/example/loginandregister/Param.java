package com.example.loginandregister;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.tea.*;
import com.aliyun.iot20180120.*;
import com.aliyun.iot20180120.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;
import com.aliyun.teautil.*;
import com.aliyun.teautil.models.*;

public class Param {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */

    public String temp,humi,tempH;
    private static final String TAG = "Para";
    public static com.aliyun.iot20180120.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "iot.cn-shanghai.aliyuncs.com";
        return new com.aliyun.iot20180120.Client(config);
    }

    public void get_data() throws Exception {
        com.aliyun.iot20180120.Client client = Param.createClient(
                "LTAI5tGCL6TbFfHar6Q3dmf7",
                "jFAfB0FwYfwL1S0ABKYdMXrZRzVtnc");
        QueryDevicePropertyStatusRequest queryDevicePropertyStatusRequest = new QueryDevicePropertyStatusRequest()
                .setIotInstanceId("iot-06z00bwqk36f7sm")
                .setProductKey("gn6kcczR3Re")
                .setDeviceName("test_01");
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            QueryDevicePropertyStatusResponse content=client.queryDevicePropertyStatusWithOptions(queryDevicePropertyStatusRequest, runtime);

            //解析json
            String text= JSON.toJSONString(content);
            JsonParameter result= JSONObject.parseObject(text,JsonParameter.class);
            Log.d(TAG, "get_data: --------"+text);
            temp=result.getBody().getData().getList().getPropertyStatusInfo().get(0).getValue();
            humi=result.getBody().getData().getList().getPropertyStatusInfo().get(1).getValue();
            tempH=result.getBody().getData().getList().getPropertyStatusInfo().get(2).getValue();
            Log.d(TAG, "get_data: --------"+temp+humi+tempH);
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
    }
}