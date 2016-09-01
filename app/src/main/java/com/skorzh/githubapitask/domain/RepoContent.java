package com.skorzh.githubapitask.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RepoContent {

    @SerializedName("tree")
    public List<RepoEntry> tree = new ArrayList<>();

}
