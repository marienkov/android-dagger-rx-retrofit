package com.skorzh.githubapitask.domain;

import com.google.gson.annotations.SerializedName;

public class RepoEntry {
    public static final String TYPE_FILE = "blob";
    public static final String TYPE_DIR = "tree";

    public static final String ROOT = "";

    @SerializedName("path")
    public String path;
    @SerializedName("type")
    public String type;

    public String name;
    public String visiblePath;
    public boolean isExpanded;

    public static Integer compareIsFile(RepoEntry entry1, RepoEntry entry2) {
        if (TYPE_FILE.equalsIgnoreCase(entry1.type)) {
            return 1;
        } else if (TYPE_FILE.equalsIgnoreCase(entry2.type)) {
            return -1;
        }
        return 0;
    }

    public boolean isFile() {
        return TYPE_FILE.equalsIgnoreCase(type);
    }


}
