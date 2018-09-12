package com.station.domain;

import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
@Data
public class StationEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String stationId;

    @Column
    private String stationName;

    @Column
    private Boolean hdEnabled;

    @Column
    private String callSign;

}
