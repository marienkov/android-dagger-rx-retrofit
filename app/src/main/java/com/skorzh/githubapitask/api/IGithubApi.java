package com.skorzh.githubapitask.api;

import com.skorzh.githubapitask.domain.RepoContent;

import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

public interface IGithubApi {

    String GITHUB_USER = "google";
    String REPOSITORY_NAME = "dagger";
    String SHA = "e2e4e9e0cc4102d5c6fb2012e7bdf029adfbebc7";

    @GET(value = "repos/" + GITHUB_USER + "/" + REPOSITORY_NAME + "/git/trees/" + SHA + "?recursive=1")
    Observable<Response<RepoContent>> getPathContent();

}
