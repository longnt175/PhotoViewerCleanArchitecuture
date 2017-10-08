package com.photoviewer.presentation.di.modules;

import android.content.Context;
import android.content.res.Resources;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {
  private Context mContext;

  public AppModule(Context context) {
    mContext = context;
  }

  @Provides Context providesApplicationContext() {
    return mContext;
  }

  @Provides @Singleton Resources getApplicationResources() {
    return mContext.getResources();
  }
}
