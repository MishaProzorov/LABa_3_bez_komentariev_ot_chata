package com.example.SunriseSunset.service;

import com.example.SunriseSunset.cache.Cache;
import com.example.SunriseSunset.dto.LocationDTO;
import com.example.SunriseSunset.model.LocationEntity;
import com.example.SunriseSunset.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final Cache sunriseSunsetCache;

    @Autowired
    public LocationService(LocationRepository locationRepository, Cache sunriseSunsetCache) {
        this.locationRepository = locationRepository;
        this.sunriseSunsetCache = sunriseSunsetCache;
    }

    public LocationEntity getLocationById(Integer id) {
        String cacheKey = "location_" + id;
        if (sunriseSunsetCache.containsKey(cacheKey)) {
            System.out.println("Returning location from cache for key: " + cacheKey);
            @SuppressWarnings("unchecked")
            List<LocationEntity> cachedLocations = (List<LocationEntity>) sunriseSunsetCache.get(cacheKey);
            return cachedLocations.get(0);
        }

        Optional<LocationEntity> entity = locationRepository.findById(id);
        if (entity.isPresent()) {
            LocationEntity location = entity.get();
            sunriseSunsetCache.put(cacheKey, Collections.singletonList(location));
            System.out.println("Saved location to cache for key: " + cacheKey);
            return location;
        }
        return null;
    }

    public LocationEntity createLocation(LocationDTO dto) {
        LocationEntity entity = new LocationEntity();
        entity.name = dto.getName();
        entity.country = dto.getCountry();
        entity.sunriseSunsets = Collections.emptyList();

        LocationEntity savedEntity = locationRepository.save(entity);
        String cacheKey = "location_" + savedEntity.id;
        sunriseSunsetCache.put(cacheKey, Collections.singletonList(savedEntity));
        System.out.println("Saved new location to cache for key: " + cacheKey);
        return savedEntity;
    }
}