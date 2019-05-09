package com.example.hquweather.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Dist {
    @Getter
    @Setter
    String id;
    @Getter
    @Setter
    String province;
    @Getter
    @Setter
    String city;
    @Getter
    @Setter
    String district;

}