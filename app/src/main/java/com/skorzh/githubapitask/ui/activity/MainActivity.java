package com.skorzh.githubapitask.ui.activity;

import android.os.Bundle;

import com.skorzh.githubapitask.R;
import com.skorzh.githubapitask.di.component.DaggerIMainActivityComponent;
import com.skorzh.githubapitask.di.component.IAppComponent;
import com.skorzh.githubapitask.di.component.IHasComponent;
import com.skorzh.githubapitask.di.component.IMainActivityComponent;
import com.skorzh.githubapitask.di.module.MainActivityModule;

public class MainActivity extends BaseActivity implements IHasComponent<IMainActivityComponent> {

    private IMainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_root);
    }

    @Override
    protected void setupComponent(IAppComponent appComponent) {
        mainActivityComponent = DaggerIMainActivityComponent.builder()
                .iAppComponent(appComponent)
                .mainActivityModule(new MainActivityModule())
                .build();
        mainActivityComponent.inject(this);
    }

    @Override
    public IMainActivityComponent getComponent() {
        return mainActivityComponent;
    }

}
