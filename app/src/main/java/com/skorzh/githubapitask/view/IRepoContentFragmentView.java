package com.skorzh.githubapitask.view;

import com.skorzh.githubapitask.domain.RepoEntry;

import java.util.List;

public interface IRepoContentFragmentView extends IBaseView {

    void updateRootRepoContent(List<RepoEntry> entities);

    void showError(String message);
}
