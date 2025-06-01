package com.example.SunriseSunset.repository;

import com.example.SunriseSunset.model.SunriseSunsetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface SunriseSunsetRepository extends JpaRepository<SunriseSunsetEntity, Integer> {

    @Query("SELECT s FROM SunriseSunsetEntity s JOIN s.locations loc " +
            "WHERE loc.name = :locationName AND s.date = :date")
    List<SunriseSunsetEntity> findByLocationNameAndDate(
            @Param("locationName") String locationName,
            @Param("date") LocalDate date);

    @Query("SELECT s FROM SunriseSunsetEntity s JOIN s.locations loc " +
            "WHERE loc.name = :locationName AND s.date BETWEEN :startDate AND :endDate")
    List<SunriseSunsetEntity> findByLocationNameAndDateRange(
            @Param("locationName") String locationName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}