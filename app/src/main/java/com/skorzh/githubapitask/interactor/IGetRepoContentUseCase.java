package com.skorzh.githubapitask.interactor;

import com.skorzh.githubapitask.domain.RepoEntry;

import java.util.List;
import java.util.Map;

import rx.Observable;

public interface IGetRepoContentUseCase {

    Observable<Map<String, List<RepoEntry>>> getRepoContent();

}
