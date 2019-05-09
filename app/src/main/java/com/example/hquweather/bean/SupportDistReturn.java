package com.example.hquweather.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupportDistReturn {
    @Getter
    @Setter
    private String reason;
    @Getter
    @Setter
    private List<Dist> result;
}
