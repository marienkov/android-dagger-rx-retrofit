package com.skorzh.githubapitask.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skorzh.githubapitask.R;
import com.skorzh.githubapitask.di.component.IMainActivityComponent;
import com.skorzh.githubapitask.domain.RepoEntry;
import com.skorzh.githubapitask.presenter.RepoContentPresenter;
import com.skorzh.githubapitask.ui.adapter.RepoContentAdapter;
import com.skorzh.githubapitask.view.IRepoContentFragmentView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepoContentFragment extends BaseFragment implements IRepoContentFragmentView {

    @Inject
    RepoContentPresenter presenter;

    @Inject
    RepoContentAdapter.IRepoEntryDataSource dataSource;

    @Bind(R.id.recycler_repo_content)
    RecyclerView recyclerRepoContent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_repo_content, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getComponent(IMainActivityComponent.class).inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);
        initRecyclerView();
    }

    @Override
    public void updateRootRepoContent(List<RepoEntry> entities) {
        ((RepoContentAdapter) recyclerRepoContent.getAdapter()).setEntries(entities);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(recyclerRepoContent, message, Snackbar.LENGTH_SHORT).show();
    }

    private void initRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerRepoContent.setLayoutManager(layoutManager);
        recyclerRepoContent.setAdapter(new RepoContentAdapter(dataSource));
    }
}
