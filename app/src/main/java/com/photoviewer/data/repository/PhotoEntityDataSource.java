package com.photoviewer.data.repository;

import com.photoviewer.data.entity.PhotoEntity;
import com.photoviewer.data.repository.datastore.DatabasePhotoEntityStore;
import com.photoviewer.data.repository.datastore.ServerPhotoEntityStore;
import com.photoviewer.domain.repository.PhotoRepository;

import org.reactivestreams.Subscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class PhotoEntityDataSource implements PhotoRepository {

    private DatabasePhotoEntityStore mDatabasePhotoEntityStore;
    private ServerPhotoEntityStore mServerPhotoEntityStore;

    @Inject
    public PhotoEntityDataSource(DatabasePhotoEntityStore databasePhotoEntityStore, ServerPhotoEntityStore serverPhotoEntityStore) {
        mDatabasePhotoEntityStore = databasePhotoEntityStore;
        mServerPhotoEntityStore = serverPhotoEntityStore;
    }

    @Override
    public Observable<List<PhotoEntity>> photos() {
        return Observable.create(new Observable.OnSubscribe<List<PhotoEntity>>() {
            @Override
            public void call(Subscriber<? super List<PhotoEntity>> subscriber) {
                queryDatabaseForAll(subscriber);
            }
        });
    }

    @Override
    public Observable<List<PhotoEntity>> searchPhotosByTitle(String title) {
        return mDatabasePhotoEntityStore.queryForTitle(title);
    }

    @Override
    public Observable<PhotoEntity> photo(int photoId) {
        return mDatabasePhotoEntityStore.queryForId(photoId);
    }

    private void queryDatabaseForAll(final Subscriber<? super List<PhotoEntity>> subscriber) {
        mDatabasePhotoEntityStore.queryForAll().subscribe(new Observer<List<PhotoEntity>>() {

            @Override
            public void onError(Throwable e) {
                subscriber.onError(e);
                fetchServerForAll(subscriber);
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(List<PhotoEntity> photoEntities) {
                if (photoEntities.size() != 0) {
                    subscriber.onNext(photoEntities);
                    subscriber.onCompleted();
                } else {
                    fetchServerForAll(subscriber);
                }
            }
        });
    }

    private void fetchServerForAll(Subscriber<? super List<PhotoEntity>> subscriber) {
        mServerPhotoEntityStore.photoEntityList().subscribe(new Observer<List<PhotoEntity>>() {
            @Override
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                subscriber.onError(e);
            }

            @Override
            public void onNext(List<PhotoEntity> photoEntities) {
                subscriber.onNext(photoEntities);
                mDatabasePhotoEntityStore.
                        saveAll(photoEntities).
                        subscribe();
            }
        });
    }
}
