package com.hb.network.api;

import android.util.Log;

import com.hb.network.base.BaseResult;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.observables.SyncOnSubscribe;
import rx.schedulers.Schedulers;

public class RxResultHelper {
    private static final String TAG = "RxResultHelper";

    public static <T> Observable.Transformer<BaseResult<T>, T> httpResult() {
        return new Observable.Transformer<BaseResult<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseResult<T>> apiResponseObservable) {
                return apiResponseObservable.flatMap(
                        new Func1<BaseResult<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(BaseResult<T> baseResult) {
                                Log.d(TAG, "call_button() called with: baseResult = [" + baseResult + "]");
                                if (baseResult.getCode() == 200) {
                                    return createData(baseResult.getData());
                                } else {
//                                    T.showShort(baseResult.getMessage());
                                    return Observable.error(new RuntimeException(String.valueOf(baseResult.getMsg())));
                                }
                            }
                        }
                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    private static <T> Observable<T> createData(final T t) {
        return Observable.create(new SyncOnSubscribe<Object, T>() {
            @Override
            protected Object generateState() {
                return null;
            }

            @Override
            protected Object next(Object o, Observer<? super T> observer) {
                try {
                    observer.onNext(t);
                    observer.onCompleted();
                } catch (Exception e) {
                    observer.onError(e);
                }
                return observer;
            }
        });
    }
}
