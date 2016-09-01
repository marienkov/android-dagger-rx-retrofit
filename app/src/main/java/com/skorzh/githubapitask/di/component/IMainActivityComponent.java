package com.skorzh.githubapitask.di.component;

import com.skorzh.githubapitask.ui.activity.MainActivity;
import com.skorzh.githubapitask.di.ActivityScope;
import com.skorzh.githubapitask.di.module.MainActivityModule;
import com.skorzh.githubapitask.ui.fragment.RepoContentFragment;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = IAppComponent.class,
        modules = MainActivityModule.class
)
public interface IMainActivityComponent {
    void inject(MainActivity activity);
    void inject(RepoContentFragment repoContentFragment);
}
