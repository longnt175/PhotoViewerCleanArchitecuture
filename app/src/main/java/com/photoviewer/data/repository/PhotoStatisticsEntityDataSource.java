package com.photoviewer.data.repository;

import com.photoviewer.data.entity.PhotoStatisticsEntity;
import com.photoviewer.data.repository.datastore.PreferencesPhotoStatisticsEntityStore;
import com.photoviewer.domain.repository.PhotoStatisticsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;


public class PhotoStatisticsEntityDataSource implements PhotoStatisticsRepository {

    @Inject PreferencesPhotoStatisticsEntityStore mStatisticsEntityStore;

    @Override
    public Observable<PhotoStatisticsEntity> readStatistics() {
        return mStatisticsEntityStore.readStatistics();
    }

    @Override
    public Observable<PhotoStatisticsEntity> updateStatistics(PhotoStatisticsEntity photoStatisticsEntity) {
        return mStatisticsEntityStore.updateStatistics(photoStatisticsEntity);
    }
}
