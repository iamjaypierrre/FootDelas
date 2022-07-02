package me.dio.footdelas.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.dio.footdelas.R;
import me.dio.footdelas.databinding.FieldItemBinding;
import me.dio.footdelas.domain.News;

public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.ViewHolder> {

    private List<News> news;
    private FavoriteListener favoriteListener;


    public FieldAdapter(List<News> news, FavoriteListener favoriteListener) {
        this.news = news;
        this.favoriteListener = favoriteListener;
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
        Context context = holder.itemView.getContext();


        News news = this.news.get(position);
        holder.binding.tvTitle.setText(news.title);
        holder.binding.tvDescription.setText(news.description);
        Picasso.get().load(news.image).into(holder.binding.ivThumbnail);

        // Implementacao da funcionalidade "Abrir Link'
        holder.binding.btOpenLink.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.link));
            context.startActivity(i);

        });


        // Implementacao da funcionalidade "Compartilhar":
        holder.binding.ivShare.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, news.link);
            context.startActivity(Intent.createChooser(i, "Compartilhar"));
        });

        // Implementacao da funcionalidade "Favoritar" (o evento sera instanciado pelo Fragment)
        holder.binding.ivFavorite.setOnClickListener(view -> {
            news.favorite = !news.favorite;
            this.favoriteListener.OnFavorite(news);
            notifyItemChanged(position);
        });
        int favoriteColor = news.favorite ? R.color.red_200 : R.color.gray;
            holder.binding.ivFavorite.setColorFilter(context.getResources().getColor(favoriteColor));



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

    public interface FavoriteListener {
        void OnFavorite(News news);
    }
}
