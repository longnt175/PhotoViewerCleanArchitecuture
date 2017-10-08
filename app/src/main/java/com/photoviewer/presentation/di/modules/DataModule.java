package com.photoviewer.presentation.di.modules;

import com.photoviewer.data.repository.PhotoEntityDataSource;
import com.photoviewer.data.repository.PhotoStatisticsEntityDataSource;
import com.photoviewer.domain.repository.PhotoRepository;
import com.photoviewer.domain.repository.PhotoStatisticsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

  @Provides
  @Singleton
  public PhotoRepository providesPhotoRepository(PhotoEntityDataSource repository) {
    return repository;
  }

  @Provides
  @Singleton
  public PhotoStatisticsRepository providesPhotoStatisticsRepository(
      PhotoStatisticsEntityDataSource repository) {
    return repository;
  }
}
