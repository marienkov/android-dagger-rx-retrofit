package com.skorzh.githubapitask.interactor;

import com.skorzh.githubapitask.api.IGithubApi;
import com.skorzh.githubapitask.domain.RepoContent;
import com.skorzh.githubapitask.domain.RepoEntry;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetRepoContentUseCase extends NetworkUseCase<RepoContent> implements IGetRepoContentUseCase {

    IGithubApi githubApi;

    @Inject
    public GetRepoContentUseCase(IGithubApi githubApi) {
        this.githubApi = githubApi;
    }

    @Override
    public Observable<Map<String, List<RepoEntry>>> getRepoContent() {
        return githubApi.getPathContent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::checkResponse)
                .flatMap(response -> Observable.from(response.body().tree))
                .flatMap(this::process)
                .groupBy(entry -> entry.visiblePath)
                .flatMap(groupedObservable -> groupedObservable.toSortedList(RepoEntry::compareIsFile))
                .toMap(entries -> entries.get(0).visiblePath);
    }

    private Observable<RepoEntry> process(RepoEntry entry) {
        entry.visiblePath = getVisiblePath(entry.path);
        entry.name = getEntryName(entry.path);
        return Observable.just(entry);
    }

    private String getVisiblePath(String path) {
        return path.lastIndexOf("/") >= 0 ? path.substring(0, path.lastIndexOf("/")) : RepoEntry.ROOT;
    }

    private String getEntryName(String path) {
        return path.lastIndexOf("/") >= 0 ? path.substring(path.lastIndexOf("/") + 1) : path;
    }

}
