package com.skorzh.githubapitask.di.module;

import com.google.gson.GsonBuilder;
import com.skorzh.githubapitask.api.IGithubApi;
import com.skorzh.githubapitask.di.ApplicationScope;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiConnectionModule {

    public static final String BASE_URL = "https://api.github.com/";
    private static final long TIMEOUT_CONNECT_MILLIS = TimeUnit.SECONDS.toMillis(10);

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(TIMEOUT_CONNECT_MILLIS, TimeUnit.MILLISECONDS);
        clientBuilder.addInterceptor(interceptor);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConnectionModule.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
        return retrofit;
    }

    @Provides
    @ApplicationScope
    public IGithubApi provideGithubApi(Retrofit retrofit) {
        return retrofit.create(IGithubApi.class);
    }
}
