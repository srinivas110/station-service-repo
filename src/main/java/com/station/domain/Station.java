package com.station.domain;

import lombok.Data;


@Data
public class Station {

    private String stationId;
    private String stationName;
    private Boolean hdEnabled;
    private String callSign;

}
