package com.skorzh.githubapitask.interactor;

import com.skorzh.githubapitask.exception.NetworkException;

import java.net.HttpURLConnection;

import retrofit2.Response;

public abstract class NetworkUseCase<T> {

    protected Response<T> checkResponse(Response<T> result) {
        if (result.code() != HttpURLConnection.HTTP_OK) {
            throw new NetworkException(result.code(), result.message());
        }
        return result;
    }
}
