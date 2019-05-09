package com.example.hquweather.presenter;

import com.example.hquweather.base.BasePresenter;
import com.example.hquweather.bean.Dist;
import com.example.hquweather.bean.WeatherResult;
import com.example.hquweather.contract.WeatherContract;
import com.example.hquweather.model.WeatherModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeatherPresenter extends BasePresenter<WeatherContract.WeatherViewInterface> implements WeatherContract.WeatherPresenterInterface {
    private Set<String> mProvinceSet=new HashSet<>();
    private Map<String,Set<String>> mProvinceToCity=new HashMap<String,Set<String>>();
    private Map<String,Set<String>> mCityToDist=new HashMap<String,Set<String>>();
    WeatherModel weatherModel=null;
    WeatherContract.WeatherModelInterface.WeatherCallback weatherCallback=new WeatherContract.WeatherModelInterface.WeatherCallback() {
        @Override
        public void onDist(List<Dist> supportDists) {
            for(Dist dist:supportDists){
                mProvinceSet.add(dist.getProvince());
                Set<String> citySet=mProvinceToCity.get(dist.getProvince());
                if(citySet==null){
                    Set<String> newCitySet=new HashSet<>();
                    newCitySet.add(dist.getCity());
                    mProvinceToCity.put(dist.getProvince(),newCitySet);
                }else{
                    citySet.add(dist.getCity());
                }
                Set<String> distSet=mCityToDist.get(dist.getCity());
                if(distSet==null){
                    Set<String> newDistSet=new HashSet<>();
                    newDistSet.add(dist.getDistrict());
                    mCityToDist.put(dist.getDistrict(),newDistSet);
                }else{
                    distSet.add(dist.getDistrict());
                }
            }
            getView().showProvince(new ArrayList<String>(mProvinceSet));
        }

        @Override
        public void onWeather(WeatherResult weatherResult) {
            getView().showCntWeather(weatherResult.getRealtime());
            getView().showFutureWeather(weatherResult.getFuture());

        }
    };
    public WeatherPresenter(WeatherContract.WeatherViewInterface v) {
        super(v);
        weatherModel=new WeatherModel();
    }

    @Override
    public void getDist() {
        weatherModel.getDist(weatherCallback);

    }

    @Override
    public void getWeather(String city) {
        weatherModel.getWeather(city,weatherCallback);
    }

    @Override
    public void getCityByProvince(String province) {
        getView().showCity(new ArrayList<String>(mProvinceToCity.get(province)));
    }

    @Override
    public void getDistByCity(String city) {
        getView().showDist(new ArrayList<String>(mCityToDist.get(city)));
    }

}
