package network;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

import java.io.IOException;

public class NetManager {
    private static volatile NetManager netManager;

    private OkHttpClient httpClient;

    private NetManager() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(20);
        dispatcher.setMaxRequestsPerHost(5);
        httpClient = new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .build();
    }

    public static NetManager getInstance() {
        if (netManager == null) {
            synchronized (NetManager.class) {
                if (netManager == null) {
                    netManager = new NetManager();
                }
            }
        }
        return netManager;
    }

    public void get(String url, DataCallBack dataCallBack) {
        httpClient.newCall(generateRequest(url)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (dataCallBack != null) {
                    dataCallBack.onFailed(ResponseCode.UNKNOWN_ERROR, e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (dataCallBack != null) {
                    if (response.isSuccessful()) {
                        try {
                            BaseData baseData = JSON.parseObject(response.body().string(), BaseData.class);
                            if (baseData.getCode() == 0) {
                                dataCallBack.onSuccess(baseData.getBody());
                            }
                        } catch (IOException e) {
                            dataCallBack.onFailed(ResponseCode.DATA_PARSE_ERROR, e.getMessage());
                        }
                    } else {
                        dataCallBack.onFailed(ResponseCode.UNKNOWN_ERROR, "网络请求异常");
                    }
                }
            }
        });


    }

    private Request generateRequest(String url) {
        return new Request.Builder().url(url).build();
    }


}
