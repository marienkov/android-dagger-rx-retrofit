package com.skorzh.githubapitask.di.component;

import com.skorzh.githubapitask.api.IGithubApi;

import retrofit2.Retrofit;

public interface IApiComponent {

    IGithubApi githubApi();

    Retrofit retrofit();

}
