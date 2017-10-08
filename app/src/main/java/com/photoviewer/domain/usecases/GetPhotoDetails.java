package com.photoviewer.domain.usecases;

import com.photoviewer.data.repository.PhotoEntityDataSource;
import com.photoviewer.domain.Photo;
import com.photoviewer.domain.mapper.photo.PhotoEntityToPhoto;
import com.photoviewer.presentation.di.modules.RxModule;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
public class GetPhotoDetails extends UseCase<Photo> {

  @Setter private int mPhotoId;
  private final PhotoEntityToPhoto mPhotoTransformer;

  private PhotoEntityDataSource mPhotoEntityDataSource;

  @Inject public GetPhotoDetails(@Named(RxModule.COMPUTATION) Scheduler executionScheduler,
      @Named(RxModule.MAIN) Scheduler observingScheduler,
      PhotoEntityDataSource photoEntityDataSource) {
    super(executionScheduler, observingScheduler);
    mPhotoTransformer = new PhotoEntityToPhoto();
    mPhotoEntityDataSource = photoEntityDataSource;
  }

  @Override protected Observable<Photo> call() {
    return this.mPhotoEntityDataSource.photo(mPhotoId).map(mPhotoTransformer::transform);
  }

}
