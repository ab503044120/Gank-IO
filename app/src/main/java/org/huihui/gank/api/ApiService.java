package org.huihui.gank.api;

import org.huihui.gank.MeiZiReponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/20.
 */

public interface ApiService {
    @GET("data/福利/{count}/{page}")
    Observable<MeiZiReponse> getMeiZi(@Path("count")int count,@Path("page") int page);
}
