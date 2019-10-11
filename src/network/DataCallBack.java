package network;


public interface DataCallBack {
    void onSuccess(String result);

    void onFailed(int code, String msg);
}
