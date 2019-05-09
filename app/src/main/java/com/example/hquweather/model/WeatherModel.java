package com.example.hquweather.model;

import android.util.Log;

import com.example.hquweather.bean.Dist;
import  com.example.hquweather.bean.SupportDistReturn;
import  com.example.hquweather.bean.WeatherReturn;
import  com.example.hquweather.contract.WeatherContract;
import com.example.hquweather.model.WeatherApi;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class WeatherModel implements WeatherContract.WeatherModelInterface {

    WeatherApi.WeatherService weatherService=null;
    public WeatherModel(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(WeatherApi.BASE_URL)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        weatherService=retrofit.create(WeatherApi.WeatherService.class);
    }

    @Override
    public void getDist(final WeatherCallback weatherCallback) {

        weatherService.getSupportDist(WeatherApi.APP_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SupportDistReturn>() {
                    @Override
                    public void onNext(SupportDistReturn supportDistReturn) {
                        Log.d("WeahterModel:",supportDistReturn.getReason());
                        if(supportDistReturn.getReason().equalsIgnoreCase("查询成功")){
                            weatherCallback.onDist(supportDistReturn.getResult());


                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

    @Override
    public void getWeather(String city, final WeatherCallback weatherCallback) {
        weatherService.getWeather(city,WeatherApi.APP_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<WeatherReturn>() {
                    @Override
                    public void onNext(WeatherReturn weatherReturn) {
                        if(weatherReturn.getReason().equalsIgnoreCase("查询成功")){
                            Log.d("WeatherModel:",weatherReturn.getResult().getCity()+"预报查询成功");
                            weatherCallback.onWeather(weatherReturn.getResult());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}