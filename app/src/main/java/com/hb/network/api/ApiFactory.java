package com.hb.network.api;

import retrofit2.Retrofit;

public class ApiFactory {

    private static volatile Retrofit mRetrofit;

    /***************************** Api ****************************/
    public static BaseApi getApi() {
        if (mRetrofit == null) {
            synchronized (Retrofit.class) {
                if (mRetrofit == null) {
                    GlpRetrofit glpRetrofit = new GlpRetrofit();
                    mRetrofit = glpRetrofit.initApi();
                }
            }
        }
        return mRetrofit.create(BaseApi.class);
    }

    /**
     * Api
     *
     * @return
     */
    private static class SingletonHolder {
        private static final BaseApi INSTANCE = getApi();
    }

    public static BaseApi getInstance() {
        return SingletonHolder.INSTANCE;
    }


}
