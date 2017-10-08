package com.photoviewer.presentation;

import android.app.Application;

import com.photoviewer.presentation.di.components.DaggerApplicationComponent;
import com.photoviewer.presentation.di.modules.AppModule;


public class PhotoViewerApplication extends Application {


    protected PhotoViewerApplication applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    protected void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public PhotoViewerApplication getApplicationComponent() {
        return this.applicationComponent;
    }

    public ViewInjector getViewInjector() {
        return DaggerActivityComponent.builder()
                .applicationComponent(this.applicationComponent).build();
    }

}
