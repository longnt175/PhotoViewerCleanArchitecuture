package com.photoviewer.presentation.di.modules;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.photoviewer.BuildConfig;
import com.photoviewer.data.network.PhotoRestService;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
  private static final String CACHE_DIR = "activate_cache";
  private static String API_BASE_URL = "http://jsonplaceholder.typicode.com";

  @Provides
  @Singleton
  PhotoRestService provideOrderRatingService(Context context, HttpLoggingInterceptor httpLoggingInterceptor) {
//    File httpCacheDirectory = new File(context.getCacheDir(), CACHE_DIR);
//    Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
    OkHttpClient client = new OkHttpClient.Builder()
//            .cache(cache)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
//            .addInterceptor(new HttpInterceptor()) //// TODO: 6/10/2017  add HttpInterceptor to support token
            .addInterceptor(httpLoggingInterceptor)
            .build();
    GsonConverterFactory factory =
            GsonConverterFactory.create(new GsonBuilder()
                    .setDateFormat("dd-MM-yyyy")
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create());
    return new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(factory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build().create(PhotoRestService.class);
  }
  @Provides @Singleton OkHttpClient provideHttpClient() {
    return createOkHttpClient();
  }

  private static OkHttpClient createOkHttpClient() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor interceptor =
          new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
      builder.addInterceptor(interceptor);
    }
    return builder.build();
  }

  @Provides @Singleton Picasso providePicasso(OkHttpClient httpClient, Context context) {

    Picasso.Builder builder = new Picasso.Builder(context);
    Picasso picasso = builder.downloader(new OkHttp3Downloader(httpClient)).build();

    return picasso;
  }
}
