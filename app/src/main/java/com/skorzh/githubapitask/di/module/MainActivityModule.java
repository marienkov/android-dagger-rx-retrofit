package com.skorzh.githubapitask.di.module;

import com.skorzh.githubapitask.di.ActivityScope;
import com.skorzh.githubapitask.domain.RepoEntry;
import com.skorzh.githubapitask.interactor.GetRepoContentUseCase;
import com.skorzh.githubapitask.interactor.IGetRepoContentUseCase;
import com.skorzh.githubapitask.presenter.RepoContentPresenter;
import com.skorzh.githubapitask.ui.adapter.RepoContentAdapter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    @ActivityScope
    public RepoContentPresenter provideRepoContentFragmentPresenter(
            GetRepoContentUseCase getRepoContentUseCase, Map<String, List<RepoEntry>> entriesMap) {
        return new RepoContentPresenter(getRepoContentUseCase, entriesMap);
    }

    @Provides
    @ActivityScope
    public RepoContentAdapter.IRepoEntryDataSource provideEntryClickListener(RepoContentPresenter presenter) {
        return presenter;
    }

    @Provides
    public IGetRepoContentUseCase provideGetRepoContentUseCase(GetRepoContentUseCase getRepoContentUseCase) {
        return getRepoContentUseCase;
    }

    @Provides
    @ActivityScope
    public Map<String, List<RepoEntry>> provideEntities() {
        return new ConcurrentHashMap<>();
    }

}
