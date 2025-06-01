package com.example.SunriseSunset.controller;

import com.example.SunriseSunset.dto.SunriseSunsetDTO;
import com.example.SunriseSunset.model.LocationEntity;
import com.example.SunriseSunset.service.LocationService;
import com.example.SunriseSunset.service.SunriseSunsetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sun/times")
public class SunriseSunsetController {

    private final SunriseSunsetService sunService;
    private final LocationService locationService;

    public SunriseSunsetController(SunriseSunsetService sunService, LocationService locationService) {
        this.sunService = sunService;
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<SunriseSunsetDTO> createSunriseSunset(@RequestBody SunriseSunsetDTO dto) {
        SunriseSunsetDTO savedDto = sunService.createSunriseSunset(dto);
        return ResponseEntity.ok(savedDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SunriseSunsetDTO> getSunriseSunsetById(@PathVariable Integer id) {
        SunriseSunsetDTO dto = sunService.getSunriseSunsetById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<SunriseSunsetDTO>> getAllSunriseSunsets() {
        return ResponseEntity.ok(sunService.getAllSunriseSunsets());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SunriseSunsetDTO> updateSunriseSunset(@PathVariable Integer id, @RequestBody SunriseSunsetDTO dto) {
        SunriseSunsetDTO updatedDto = sunService.updateSunriseSunset(id, dto);
        return updatedDto != null ? ResponseEntity.ok(updatedDto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSunriseSunset(@PathVariable Integer id) {
        sunService.deleteSunriseSunset(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-location-and-date")
    public ResponseEntity<List<SunriseSunsetDTO>> getSunriseSunsetByLocationAndDate(
            @RequestParam("locationName") String locationName,
            @RequestParam("date") String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        List<SunriseSunsetDTO> dtos = sunService.getSunriseSunsetByLocationAndDate(locationName, parsedDate);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/by-location-and-date-range")
    public ResponseEntity<List<SunriseSunsetDTO>> getSunriseSunsetByLocationAndDateRange(
            @RequestParam("locationName") String locationName,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        LocalDate parsedStartDate = LocalDate.parse(startDate);
        LocalDate parsedEndDate = LocalDate.parse(endDate);
        List<SunriseSunsetDTO> dtos = sunService.getSunriseSunsetByLocationAndDateRange(locationName, parsedStartDate, parsedEndDate);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/clear-cache")
    public ResponseEntity<String> clearCache() {
        sunService.clearCache();
        return ResponseEntity.ok("Cache cleared successfully");
    }

    @GetMapping("/location/{id}")
    public ResponseEntity<LocationEntity> getLocationById(@PathVariable Integer id) {
        LocationEntity location = locationService.getLocationById(id);
        return location != null ? ResponseEntity.ok(location) : ResponseEntity.notFound().build();
    }
}