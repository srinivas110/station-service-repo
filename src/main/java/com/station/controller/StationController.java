package com.station.controller;

import com.station.domain.Station;
import com.station.domain.StationEntity;
import com.station.service.StationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "${microservice.contextPath}/station", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class StationController {

    @Autowired
    StationService stationService;

    @PostMapping
    @ApiOperation(
            value = "Create Station",
            notes = "Adds new station",
            consumes = "application/json",
            produces = "application/json",
            response = ResponseEntity.class,
            responseContainer = "ResponseEntity")
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Success fully created station"),
    })
    public ResponseEntity createStation(
            @RequestBody Station station) {
        stationService.createStation(station);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{stationId}")
    @ApiOperation(
            value = "Update Station",
            notes = "Updates existing station",
            consumes = "application/json",
            produces = "application/json",
            response = ResponseEntity.class,
            responseContainer = "ResponseEntity")
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Success fully created station"),
    })
    public ResponseEntity updatedStation(
            @RequestBody Station station) {
        stationService.updateStation(station);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(
            value = "Find All stations",
            notes = "Adds new station",
            consumes = "application/json",
            produces = "application/json",
            response = ResponseEntity.class,
            responseContainer = "ResponseEntity")
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Success fully retrived station"),
    })
    public ResponseEntity<List<Station>> getStationDetails() {
        return new ResponseEntity(stationService.getAllStation(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{stationId}")
    @ApiOperation(
            value = "Deletets station",
            notes = "Deletes station",
            consumes = "application/json",
            produces = "application/json",
            response = ResponseEntity.class,
            responseContainer = "ResponseEntity")
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Success fully Deleted station"),
    })
    public ResponseEntity deleteStation(@PathVariable(value = "stationId") String stationId) {
        stationService.deleteStationById(stationId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    @ApiOperation(
            value = "Find station by Id",
            notes = "Adds new station",
            consumes = "application/json",
            produces = "application/json",
            response = ResponseEntity.class,
            responseContainer = "ResponseEntity")
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Success fully retrived station"),
    })
    public ResponseEntity<List<Station>> searchStation(
            @RequestParam(value = "stationId", required = false)
                    String stationId,
            @RequestParam(value = "name", required = false)
                    String stationName,
    @RequestParam(value = "hdEnabled", required = false)
                    Boolean hdEnabled) {
        List<Station> stations = new ArrayList<>();
        if (null != stationId) {
            stations = Arrays.asList(stationService.searchStationById(stationId));
        } else if (null != stationName) {
            StationEntity stationEntity=new StationEntity();
            stationEntity.setStationName(stationName);
            stations = stationService.searchStation(stationEntity);
        }else if (null != hdEnabled) {
            StationEntity stationEntity=new StationEntity();
            stationEntity.setHdEnabled(hdEnabled);
            stations = stationService.searchStation(stationEntity);
        }

        return new ResponseEntity(stations, HttpStatus.OK);
    }

}
