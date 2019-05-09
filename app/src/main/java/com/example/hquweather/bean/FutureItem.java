package com.example.hquweather.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class FutureItem {
    @Getter
    @Setter
    private String date;
    @Getter
    @Setter
    private String temperature;
    @Getter
    @Setter
    private String weather;
    @Getter
    @Setter
    private Wid wid;
    @Getter
    @Setter
    private String direct;
}