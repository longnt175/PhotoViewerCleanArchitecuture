package com.photoviewer.presentation.di.components;

import com.photoviewer.presentation.view.activity.DiAppCompatActivity;
import com.photoviewer.presentation.view.activity.PhotoDetailsActivity;
import com.photoviewer.presentation.view.activity.PhotosListActivity;

/**
 * Created by mobiledev on 6/10/2017.
 */

interface ViewInjector {
    void inject(DiAppCompatActivity diAppCompatActivity);

    void inject(PhotosListActivity photosListActivity);
    //

    void inject(PhotoDetailsActivity photoDetailsActivity);
}
