package com.station.service;


import com.station.domain.Station;
import com.station.domain.StationEntity;
import com.station.persist.StationRepository;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class StationService {

    @Autowired
    StationRepository stationRepository;

    public void createStation(Station station) {
        StationEntity stationEntity = new StationEntity();
        stationEntity.setStationName(station.getStationName());
        stationEntity.setCallSign(station.getCallSign());
        stationEntity.setHdEnabled(station.getHdEnabled());
        stationRepository.save(stationEntity);
    }

    public void updateStation(Station station) {
        StationEntity stationEntity = new StationEntity();
        stationEntity.setStationName(station.getStationName());
        stationEntity.setStationId(station.getStationId());
        stationEntity.setCallSign(station.getCallSign());
        stationEntity.setHdEnabled(station.getHdEnabled());
        stationRepository.save(stationEntity);
    }

    public void deleteStationById(String id) {
        stationRepository.delete(id);
    }

    public Station searchStationById(String id) {
        Observable<Station> stationObservable=
                Observable.create(new ObservableOnSubscribe<Station>() {
           @Override
           public void subscribe(ObservableEmitter<Station> e) throws Exception {
               e.onNext(getStationDetails(stationRepository.findOne(id)));
               e.onComplete();
           }
       }).doOnComplete(()->{
           log.info("Completed call");
       }).subscribeOn(Schedulers.io());
        log.info("Waiting for results");
       return stationObservable.blockingFirst();
    }

    public List<Station> searchStation(StationEntity stationEntity) {
        Example<StationEntity> example=Example.of(stationEntity, ExampleMatcher.matchingAll());
        return fetchAllStations(stationRepository.findAll(example));
    }

    public List<Station> getAllStation() {
        return fetchAllStations(stationRepository.findAll());

    }

    private List<Station> fetchAllStations(List<StationEntity> all) {
        List<Station> stations = new ArrayList<>();
        all.forEach(stationEntity -> {
            stations.add(getStationDetails(stationEntity));
        });
        return stations;
    }

    private Station getStationDetails(StationEntity stationEntity) {
        Station station = new Station();
        station.setStationId(stationEntity.getStationId());
        station.setStationName(stationEntity.getStationName());
        station.setCallSign(stationEntity.getCallSign());
        station.setHdEnabled(stationEntity.getHdEnabled());
        log.info("Returning results stationId={}",station.getStationId());
        return station;
    }
}
