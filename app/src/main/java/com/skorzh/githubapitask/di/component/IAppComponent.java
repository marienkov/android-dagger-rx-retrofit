package com.skorzh.githubapitask.di.component;

import com.skorzh.githubapitask.App;
import com.skorzh.githubapitask.di.ApplicationScope;
import com.skorzh.githubapitask.di.module.ApiConnectionModule;
import com.skorzh.githubapitask.di.module.AppModule;

import dagger.Component;

@ApplicationScope
@Component(
        modules = {
                AppModule.class,
                ApiConnectionModule.class
        }
)
public interface IAppComponent extends IApiComponent {
    void inject(App app);
}