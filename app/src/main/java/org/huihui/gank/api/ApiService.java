package org.huihui.gank.api;

import org.huihui.gank.MeiZiReponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/20.
 */

public interface ApiService {
    @GET("http://gank.io/api/data/福利/10/1")
    Observable<MeiZiReponse> getMeiZi(int page);
}
