package com.photoviewer.presentation.di.modules;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class RxModule {
  public static final String MAIN = "main";
  public static final String IO = "io";
  public static final String COMPUTATION = "computation";
  public static final String TRAMPOLINE = "trampoline";

  @Provides @Singleton @Named(RxModule.MAIN) Scheduler provideMainScheduler() {
    return AndroidSchedulers.mainThread();
  }

  @Provides @Singleton @Named(RxModule.IO) Scheduler provideIoScheduler() {
    return Schedulers.io();
  }

  @Provides @Singleton @Named(RxModule.COMPUTATION) Scheduler provideComputationScheduler() {
    return Schedulers.computation();
  }

  @Provides @Singleton @Named(RxModule.TRAMPOLINE) Scheduler provideTrampolineScheduler() {
    return Schedulers.trampoline();
  }
}

