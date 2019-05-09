package com.example.hquweather.contract;

import com.example.hquweather.bean.Dist;
import com.example.hquweather.bean.FutureItem;
import com.example.hquweather.bean.RealTimeItem;
import com.example.hquweather.bean.WeatherResult;

import java.util.List;

public interface WeatherContract {
    interface WeatherViewInterface{
        void showProvince(List<String> provinceList);
        void showCity(List<String> cityList);
        void showDist(List<String> distList);
        void showCntWeather(RealTimeItem realTimeItem);
        void showFutureWeather(List<FutureItem> futureItemList);

    }
    interface WeatherPresenterInterface{
        void getDist();
        void getWeather(String city);
        void getCityByProvince(String province);
        void getDistByCity(String city);
    }
    interface WeatherModelInterface{
        void getDist(WeatherCallback weatherCallback);
        void getWeather(String city,WeatherCallback weatherCallback);
        interface WeatherCallback{
            void onDist(List<Dist> supportDists);
            void onWeather(WeatherResult weatherResult);

        }

    }


}