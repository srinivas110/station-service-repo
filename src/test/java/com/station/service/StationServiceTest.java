package com.station.service;

import static org.junit.Assert.*;

import com.station.domain.Station;
import com.station.persist.StationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class StationServiceTest {

    @InjectMocks
    StationService stationService;

    @Mock
    StationRepository stationRepository;

    @Test
    public void createStation() throws Exception {
        stationService.createStation(getStation());
    }

    @Test
    public void updateStation() throws Exception {
    }

    @Test
    public void deleteStationById() throws Exception {
    }

    @Test
    public void searchStationById() throws Exception {
    }

    @Test
    public void searchStation() throws Exception {
    }

    @Test
    public void getAllStation() throws Exception {
    }

    private Station getStation() {
        Station station=new Station();
        station.setStationName("Test");
        station.setHdEnabled(true);
        return station;
    }

}