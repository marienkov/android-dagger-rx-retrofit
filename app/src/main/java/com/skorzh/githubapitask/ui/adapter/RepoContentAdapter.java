package com.skorzh.githubapitask.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skorzh.githubapitask.R;
import com.skorzh.githubapitask.domain.RepoEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepoContentAdapter extends RecyclerView.Adapter<RepoContentAdapter.RepoEntryViewHolder> {

    private IRepoEntryDataSource dataSource;

    private List<RepoEntry> entries = new ArrayList<>();

    public RepoContentAdapter(IRepoEntryDataSource source) {
        this.dataSource = source;
    }

    @Override
    public RepoEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo_entry, parent, false);
        return new RepoEntryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RepoEntryViewHolder holder, int position) {
        final RepoEntry entry = entries.get(position);
        final boolean isFile = entry.isFile();
        holder.imageEntryIcon.setImageResource(isFile ? R.drawable.ic_menu_mark : R.drawable.ic_menu_archive);
        holder.textEntryName.setText(entry.name);

        final RepoContentAdapter adapter = new RepoContentAdapter(dataSource);
        holder.recyclerEntries.setAdapter(adapter);

        holder.rootView.setOnClickListener(isFile ? null : v -> onEntryClick(holder, entry));

        if (entry.isExpanded) {
            holder.getAdapter().setEntries(dataSource.getEntries(entry.path));
        }
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    private void onEntryClick(RepoEntryViewHolder holder, RepoEntry entry) {
        if (entry.isExpanded) {
            holder.getAdapter().clearEntities();
        } else {
            holder.getAdapter().setEntries(dataSource.getEntries(entry.path));
        }
        entry.isExpanded = !entry.isExpanded;
    }

    public void setEntries(List<RepoEntry> entries) {
        this.entries.clear();
        this.entries.addAll(entries);
        notifyDataSetChanged();
    }

    public void clearEntities() {
        this.entries.clear();
        notifyDataSetChanged();
    }

    public static class RepoEntryViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image_entry_icon)
        ImageView imageEntryIcon;

        @Bind(R.id.text_entry_name)
        TextView textEntryName;

        @Bind(R.id.recycler_repo_content)
        RecyclerView recyclerEntries;

        private View rootView;

        public RepoEntryViewHolder(View v) {
            super(v);
            this.rootView = v;
            ButterKnife.bind(this, v);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(recyclerEntries.getContext());
            layoutManager.setAutoMeasureEnabled(true);
            recyclerEntries.setLayoutManager(layoutManager);
        }

        public RepoContentAdapter getAdapter() {
            return (RepoContentAdapter) recyclerEntries.getAdapter();
        }
    }

    public interface IRepoEntryDataSource {
        List<RepoEntry> getEntries(String path);
    }

}
