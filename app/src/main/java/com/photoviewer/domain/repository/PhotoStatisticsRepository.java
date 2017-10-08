package com.photoviewer.domain.repository;

import com.photoviewer.data.entity.PhotoStatisticsEntity;

import io.reactivex.Observable;

public interface PhotoStatisticsRepository {
    /**
     * Get an {@link io.reactivex.Observable} which will emit {@link PhotoStatisticsEntity}.
     */
    Observable<PhotoStatisticsEntity> readStatistics();

    /**
     * Get an {@link io.reactivex.Observable} which will emit {@link PhotoStatisticsEntity}.
     */
    Observable<PhotoStatisticsEntity> updateStatistics(PhotoStatisticsEntity photoStatisticsEntity);
}
