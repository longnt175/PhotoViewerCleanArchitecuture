package com.photoviewer.presentation.di.components;

import android.content.Context;

import com.photoviewer.presentation.di.modules.AppModule;
import com.photoviewer.presentation.di.modules.DataModule;
import com.photoviewer.presentation.di.modules.NetworkModule;
import com.photoviewer.presentation.di.modules.RxModule;
import com.photoviewer.presentation.di.modules.UseCasesModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mobiledev on 6/10/2017.
 */


@Singleton
@Component(modules = {AppModule.class, DataModule.class, NetworkModule.class, RxModule.class, UseCasesModule.class})
public interface ApplicationComponent {
    Context context();

}

