package com.skorzh.githubapitask.presenter;

import android.util.Log;

import com.skorzh.githubapitask.domain.RepoEntry;
import com.skorzh.githubapitask.exception.NetworkException;
import com.skorzh.githubapitask.interactor.IGetRepoContentUseCase;
import com.skorzh.githubapitask.ui.adapter.RepoContentAdapter;
import com.skorzh.githubapitask.view.IRepoContentFragmentView;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class RepoContentPresenter implements IRepoContentPresenter, RepoContentAdapter.IRepoEntryDataSource {

    private final IGetRepoContentUseCase getRepoContentUseCase;
    private IRepoContentFragmentView view;
    private Map<String, List<RepoEntry>> entriesMap;

    @Inject
    public RepoContentPresenter(IGetRepoContentUseCase getRepoContentUseCase, Map<String, List<RepoEntry>> entriesMap) {
        this.getRepoContentUseCase = getRepoContentUseCase;
        this.entriesMap = entriesMap;
    }

    private void loadRepoContent() {
        getRepoContentUseCase.getRepoContent().subscribe(
                this::processResult,
                this::processError);
    }

    private void loadRootRepoContent() {
        loadRepoContent();
        entriesMap.clear();
    }

    private void processError(Throwable throwable) {
        if (throwable instanceof NetworkException) {
            final NetworkException exception = (NetworkException) throwable;
            view.showError(exception.getCode() + ": " + exception.getMessage());
        }
    }

    private void processResult(Map<String, List<RepoEntry>> result) {
        entriesMap = result;
        view.updateRootRepoContent(entriesMap.get(RepoEntry.ROOT));
    }

    @Override
    public void init(IRepoContentFragmentView view) {
        this.view = view;
        Log.d("presenter", "init()");
        loadRootRepoContent();
    }

    @Override
    public List<RepoEntry> getEntries(String path) {
        return entriesMap.get(path);
    }
}
