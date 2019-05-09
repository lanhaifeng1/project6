package com.example.hquweather.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class WeatherReturn {
    @Getter
    @Setter
    private String reason;
    @Getter
    @Setter
    private WeatherResult result;

}
