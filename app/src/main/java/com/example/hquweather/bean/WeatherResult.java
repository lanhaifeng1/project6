package com.example.hquweather.bean;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class WeatherResult{
    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private RealTimeItem realtime;
    @Getter
    @Setter
    private List<FutureItem> future;


}