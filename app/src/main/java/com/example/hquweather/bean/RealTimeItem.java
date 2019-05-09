package com.example.hquweather.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class RealTimeItem{
    @Getter
    @Setter
    private String temperature;
    @Getter
    @Setter
    private String humidity;
    @Getter
    @Setter
    private String info;
    @Getter
    @Setter
    private String wid;
    @Getter
    @Setter
    private String direct;
    @Getter
    @Setter
    private String power;
    @Getter
    @Setter
    private String aqi;
}
