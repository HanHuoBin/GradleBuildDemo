package com.hb.network.api;

import android.text.TextUtils;

import com.hb.base.utils.T;
import com.hb.base.utils.network.NetWorkUtil;
import com.hb.network.App;
import com.hb.network.Config;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.internal.platform.Platform.INFO;

public class GlpRetrofit {

    private OkHttpClient insideClient       = null;

    Interceptor          mInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder request = chain.request().newBuilder();
            if (!TextUtils.isEmpty(App.token)) {
                request.addHeader("Accept-Token", App.token);
            }
            return chain.proceed(request.build());
        }
    } ;

    /**
     * Api
     * 
     * @return
     */
    public Retrofit initApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
	        @Override
	        public void log(String message) {
		        Platform.get().log(INFO, decodeUnicodeToString(message), null);
	        }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(App.getInstance().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        insideClient = new OkHttpClient
                .Builder()
                .readTimeout(20000, TimeUnit.MILLISECONDS)
                .connectTimeout(20000, TimeUnit.MILLISECONDS)
                .addInterceptor(mInterceptor)
                .addInterceptor(interceptor)
                .addInterceptor(new BaseUrlInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.API_DEVICE + Config.VERSION)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(insideClient)
                .build();
        return retrofit;
    }
	
	public String decodeUnicodeToString(String uString) {
		StringBuilder sb = new StringBuilder();
		int i = -1, pos = 0;
		while ((i = uString.indexOf("\\u", pos)) != -1) {
			sb.append(uString.substring(pos, i));
			if (i + 5 < uString.length()) {
				pos = i + 6;
				sb.append((char) Integer.parseInt(uString.substring(i + 2, i + 6), 16));
			}
		}
		sb.append(uString.substring(pos));
		return sb.toString();
	}


    class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.isNetConnected(App.getInstance())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                T.showShort("网络异常");
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetConnected(App.getInstance())) {
                //有网的时候
            }
            return originalResponse.newBuilder().build();
        }
    }
}
