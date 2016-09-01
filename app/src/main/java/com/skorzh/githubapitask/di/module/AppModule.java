package com.skorzh.githubapitask.di.module;

import android.app.Application;
import android.content.Context;

import com.skorzh.githubapitask.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

    @Provides
    public Context provideAppContext() {
        return app.getApplicationContext();
    }


}