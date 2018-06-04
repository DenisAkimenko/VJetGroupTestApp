package com.example.denis.vjetgrouptestapp.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.denis.vjetgrouptestapp.R;
import com.example.denis.vjetgrouptestapp.data.model.Source;

import java.util.ArrayList;
import java.util.List;

import static com.example.denis.vjetgrouptestapp.data.constants.ApiConstants.MAX_SOURCES;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {

    private List<Source> sources = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_source, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Source source = sources.get(position);
        holder.tvSourceName.setText(source.getName());
        holder.cbSource.setChecked(source.isSelected());

        View.OnClickListener listener = view -> {
            boolean wasSelected = sources.get(position).isSelected();
            if (wasSelected){
                sources.get(position).setSelected(false);
                notifyItemChanged(position);
            } else {
                if (checkMaxSize()) {
                    sources.get(position).setSelected(!sources.get(position).isSelected());
                    notifyItemChanged(position);
                }
            }
            holder.cbSource.setChecked(sources.get(position).isSelected());
        };

        holder.itemView.setOnClickListener(listener);
        holder.cbSource.setOnClickListener(listener);
    }

    private boolean checkMaxSize() {
        int count = 0;
        for (Source source : sources) {
            if (source.isSelected()) {
                count++;
            }
        }
        return count < MAX_SOURCES;
    }

    public void setSources(List<Source> sources){
        this.sources = sources;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (sources == null) return 0;
        return sources.size();
    }

    public List<Source> getSources(){
        List<Source> filteredSources = new ArrayList<>();
        for (Source source : sources) {
            if (source.isSelected()) {
                filteredSources.add(source);
            }
        }
        return filteredSources;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSourceName;
        CheckBox cbSource;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSourceName = itemView.findViewById(R.id.tv_source_name);
            cbSource = itemView.findViewById(R.id.checkbox);
        }
    }
}
