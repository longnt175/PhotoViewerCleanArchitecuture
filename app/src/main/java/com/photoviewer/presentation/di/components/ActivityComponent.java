package com.photoviewer.presentation.di.components;

/**
 * Created by mobiledev on 6/10/2017.
 */


import com.photoviewer.presentation.di.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent extends ViewInjector {}
