package com.example.SunriseSunset.controller;

import com.example.SunriseSunset.dto.LocationDTO;
import com.example.SunriseSunset.model.LocationEntity;
import com.example.SunriseSunset.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<LocationEntity> createLocation(@RequestBody LocationDTO dto) {
        LocationEntity savedLocation = locationService.createLocation(dto);
        return savedLocation != null ? ResponseEntity.ok(savedLocation) : ResponseEntity.badRequest().build();
    }
}