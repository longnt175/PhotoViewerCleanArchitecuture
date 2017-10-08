package com.photoviewer.di;

import com.photoviewer.common.steps.PageObject;
import com.photoviewer.pages.PhotosListPage;
import com.photoviewer.presentation.di.components.ApplicationComponent;
import com.photoviewer.presentation.di.modules.AppModule;

import dagger.Component;
import dagger.Module;

@Module
@Component(modules = { PageObject.class, PhotosListPage.class })
public class TestsModule {
}
