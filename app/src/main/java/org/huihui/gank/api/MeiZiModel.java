package org.huihui.gank.api;

import org.huihui.gank.MeiZiReponse;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/21.
 */

public class MeiZiModel {

    private final Retrofit mRxRetrofit;
    private final ApiService mApiService;

    public MeiZiModel() {
        mRxRetrofit = RetrofitClient.getRxRetrofit("http://gank.io/api/");
        mApiService = mRxRetrofit.create(ApiService.class);
    }

    public Observable<MeiZiReponse> getMeiZi(int count, int page) {
        return mApiService.getMeiZi(count, page).compose(new SchedulerTransformer<MeiZiReponse>());
    }

}
