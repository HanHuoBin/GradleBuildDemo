package com.hb.network.api;

import android.util.Log;

import com.hb.network.Config;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hanbin on 2018/3/27.
 */

public class BaseUrlInterceptor implements Interceptor {
    private static final String TAG = "BaseUrlInterceptor";
    private static final String URL_SOURCE_API = "api";
    private static final String URL_SOURCE_UPDATE = "update";
    private static final String URL_SOURCE_WEATHER = "weather";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();      //获取Request对象
        HttpUrl oldHttpUrl = request.url();         //获取url
        Request.Builder builder = request.newBuilder();//通过request创建builder
        List<String> headers = request.headers("bqs_auth");
        Log.e(TAG, "headers:" + headers.size());
        if (headers != null && headers.size() > 0) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader("bgs_auth");
            //匹配获得新的BaseUrl
            String headerValue = headers.get(0);
            HttpUrl newHttpUrl = null;
            switch (headerValue) {
                case URL_SOURCE_API:
                    newHttpUrl = HttpUrl.parse(Config.API_API);
                    break;
                case URL_SOURCE_UPDATE:

                    break;
                case URL_SOURCE_WEATHER:

                    break;
                default:
                    newHttpUrl = oldHttpUrl;
                    break;
            }
            HttpUrl newFullUrl = oldHttpUrl
                    .newBuilder()
                    .scheme("https")//更换网络协议
                    .host(newHttpUrl.host())//更换主机名
                    .port(newHttpUrl.port())//更换端口
                    .removePathSegment(0)//移除第一个参数
                    .build();
            //重建request
            return chain.proceed(builder.url(newFullUrl).build());

        }
        return chain.proceed(request);
    }
}
