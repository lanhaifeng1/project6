package com.example.hquweather.model;

import cn.edu.hqu.cst.hquweather.bean.SupportDistReturn;
import cn.edu.hqu.cst.hquweather.bean.WeatherReturn;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherApi {
    public final static String APP_KEY="ee79ed461cb614f8a5a1d1098b14b585";//此处APP_KEY需要自己到《聚合数据》网站注册获取
    public final static String BASE_URL="http://apis.juhe.cn/";
    public final static String SUPPORT_CITY="simpleWeather/cityList";
    public final static String WEATHER="simpleWeather/query";
    public interface WeatherService{
        @GET(SUPPORT_CITY)
        Observable<SupportDistReturn> getSupportDist(@Query("key")String app_key);
        @GET(WEATHER)
        Observable<WeatherReturn> getWeather(@Query("city")String city,@Query("key")String app_key);
    }
}

