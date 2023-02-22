package com.example.loginandregister;

// This file is auto-generated, don't edit it. Thanks.

import android.util.Log;

import com.aliyun.tea.*;
import com.aliyun.iot20180120.*;
import com.aliyun.iot20180120.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;
import com.aliyun.teautil.*;
import com.aliyun.teautil.models.*;

public class TempThreshold {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */

    private static final String TAG = "TempThreshold";
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

    public void set_tempH(int temp) throws Exception {
        com.aliyun.iot20180120.Client client = TempThreshold.createClient("LTAI5tGCL6TbFfHar6Q3dmf7", "jFAfB0FwYfwL1S0ABKYdMXrZRzVtnc");
        SetDevicePropertyRequest setDevicePropertyRequest = new SetDevicePropertyRequest()
                .setIotInstanceId("iot-06z00bwqk36f7sm")
                .setProductKey("gn6kcczR3Re")
                .setDeviceName("test_01")
                .setItems("{\"TempThreshold\":"+temp+"}");
        Log.d(TAG, "set_tempH: "+temp);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            client.setDevicePropertyWithOptions(setDevicePropertyRequest, runtime);
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
