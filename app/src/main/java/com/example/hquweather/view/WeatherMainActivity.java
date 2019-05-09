package com.example.hquweather.view;

import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import com.example.hquweather.R;
import com.example.hquweather.Adapter.CityAdapter;
import com.example.hquweather.Adapter.DistAdapter;
import com.example.hquweather.Adapter.ProvinceAdapter;
import com.example.hquweather.base.BaseActivity;
import com.example.hquweather.base.BasePresenter;
import com.example.hquweather.bean.Dist;
import com.example.hquweather.bean.FutureItem;
import com.example.hquweather.bean.RealTimeItem;
import com.example.hquweather.contract.WeatherContract;
import com.example.hquweather.presenter.WeatherPresenter;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherMainActivity extends BaseActivity<WeatherContract.WeatherViewInterface,WeatherPresenter> implements WeatherContract.WeatherViewInterface {


    WeatherPresenter mWeatherPresenter;
    @BindView(R.id.tv_weather_main_cnt_temp)
    TextView mTvWeatherMainCntTemp;
    @BindView(R.id.tv_weather_main_cnt_info)
    TextView mTvWeatherMainCntInfo;
    @BindView(R.id.sp_weather_main_province)
    Spinner mSpWeatherMainProvince;
    @BindView(R.id.sp_weather_main_city)
    Spinner mSpWeatherMainCity;
    @BindView(R.id.sp_weather_main_dist)
    Spinner mSpWeatherMainDist;
    @BindView(R.id.lc_weather)
    LineChart mLcWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main);
        ButterKnife.bind(this);
        initChart();
        mWeatherPresenter=getPresenter();
        mWeatherPresenter.getDist();


    }

    @Override
    protected WeatherPresenter createPresenter() {
        return new WeatherPresenter(this);
    }


    @Override
    public void showProvince(List<String> provinceList) {
        mSpWeatherMainProvince.setAdapter(new ProvinceAdapter(this,provinceList));

    }

    @Override
    public void showCity(List<String> cityList) {
        mSpWeatherMainCity.setAdapter(new CityAdapter(this,cityList));

    }

    @Override
    public void showDist(List<String> distList) {
        mSpWeatherMainDist.setAdapter(new DistAdapter(this,distList));

    }

    @Override
    public void showCntWeather(RealTimeItem realTimeItem) {
        mTvWeatherMainCntTemp.setText(realTimeItem.getTemperature());
        mTvWeatherMainCntInfo.setText(realTimeItem.getInfo());

    }

    @Override
    public void showFutureWeather(List<FutureItem> futureItemList) {
        List<String> xVals=new ArrayList<String>();
        List<Entry> highTemp=new ArrayList<Entry>();
        List<Entry> lowTemp=new ArrayList<Entry>();
        for(int i=0;i<futureItemList.size();i++){
            String tempStr=futureItemList.get(i).getTemperature();
            tempStr=tempStr.substring(0,tempStr.length()-1);
            String[] lowHighTemp=tempStr.split("\\/");
            lowTemp.add(new Entry(i,Float.parseFloat(lowHighTemp[0])));
            highTemp.add(new Entry(i,Float.parseFloat(lowHighTemp[1])));
        }
        LineDataSet high=new LineDataSet(highTemp,getString(R.string.hightemp));
        LineDataSet low=new LineDataSet(lowTemp,getString(R.string.lowtemp));
        List<ILineDataSet> dataSets=new ArrayList<>();
        dataSets.add(high);
        dataSets.add(low);
        LineData lineData=new LineData(dataSets);
        mLcWeather.setData(lineData);
        mLcWeather.invalidate();
    }
    private void initChart(){
        Description description=new Description();
        description.setText(getString(R.string.mainActivity_linechat_description));
        description.setEnabled(true);
        mLcWeather.setDescription(description);
        mLcWeather.setNoDataText(getString(R.string.mainActivity_linechat_noData));
        mLcWeather.setTouchEnabled(false);
        mLcWeather.setDragDecelerationFrictionCoef(0.9f);
        mLcWeather.setDragEnabled(false);
        mLcWeather.setScaleEnabled(false);
        mLcWeather.animateX(3000);
        mLcWeather.setBackgroundColor(Color.LTGRAY);
        mLcWeather.setPinchZoom(false);
        mLcWeather.setDrawGridBackground(true);
        mLcWeather.setHighlightPerDragEnabled(true);
        mLcWeather.setDefaultFocusHighlightEnabled(true);
    }
    @OnItemSelected(R.id.sp_weather_main_province)
    void selectProvince(int position){
        mWeatherPresenter.getCityByProvince(mSpWeatherMainProvince.getItemAtPosition(position).toString());

    }
    @OnItemSelected(R.id.sp_weather_main_city)
    void selectCity(int position){
        mWeatherPresenter.getDistByCity(mSpWeatherMainCity.getItemAtPosition(position).toString());

    }
    @OnItemSelected(R.id.sp_weather_main_dist)
    void selectDist(int position){
        mWeatherPresenter.getWeather(mSpWeatherMainDist.getItemAtPosition(position).toString());

    }
}
