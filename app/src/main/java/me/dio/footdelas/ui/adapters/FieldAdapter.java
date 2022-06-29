package me.dio.footdelas.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.dio.footdelas.databinding.FieldItemBinding;
import me.dio.footdelas.domain.News;

public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.ViewHolder> {

    private List<News> news;

    public FieldAdapter(List<News> news) {
        this.news = news;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FieldItemBinding binding = FieldItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = this.news.get(position);
        holder.binding.tvTitle.setText(news.getTitle());
        holder.binding.tvDescription.setText(news.getDescription());

    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final FieldItemBinding binding;

        public ViewHolder(FieldItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
