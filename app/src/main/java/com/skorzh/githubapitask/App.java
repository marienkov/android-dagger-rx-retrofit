package com.skorzh.githubapitask;

import android.app.Application;
import android.content.Context;

import com.skorzh.githubapitask.di.component.DaggerIAppComponent;
import com.skorzh.githubapitask.di.component.IAppComponent;
import com.skorzh.githubapitask.di.module.ApiConnectionModule;
import com.skorzh.githubapitask.di.module.AppModule;

public class App extends Application {

    private IAppComponent appComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildGraphAndInject();
    }

    public IAppComponent getAppComponent() {
        return appComponent;
    }

    public void buildGraphAndInject() {
        appComponent = DaggerIAppComponent.builder()
                .appModule(new AppModule(this))
                .apiConnectionModule(new ApiConnectionModule())
                .build();
        appComponent.inject(this);
    }

}
