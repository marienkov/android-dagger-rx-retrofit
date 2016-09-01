package com.skorzh.githubapitask.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.skorzh.githubapitask.App;
import com.skorzh.githubapitask.di.component.IAppComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(App.get(this).getAppComponent());
    }

    protected abstract void setupComponent(IAppComponent appComponent);
}
