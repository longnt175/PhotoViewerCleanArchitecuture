package com.photoviewer.presentation.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by mobiledev on 6/10/2017.
 */

@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
