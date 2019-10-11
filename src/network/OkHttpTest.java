package network;


import okhttp3.*;

import java.io.IOException;

public class OkHttpTest {

    public static void main(String[] args) {
//        NetManager.getInstance().get(Constants.TEST_URL, new DataCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                System.out.println(result);
//            }
//
//            @Override
//            public void onFailed(int code, String msg) {
//                System.out.println(code + ": " + msg);
//            }
//        });

//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(Constants.TEST_URL).build();
//        try {
//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                System.out.println(response.body().string());
//            } else {
//                System.out.println("网络异常");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(Constants.TEST_URL).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("网络异常");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println(response.body().string());
                } else {
                    System.out.println("网络异常");
                }

            }
        });
    }
}
