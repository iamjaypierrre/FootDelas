package me.dio.footdelas.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import me.dio.footdelas.MainActivity;
import me.dio.footdelas.databinding.FragmentFavoritesBinding;
import me.dio.footdelas.domain.News;
import me.dio.footdelas.ui.adapters.FieldAdapter;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        loadFavoriteNews();

        return binding.getRoot();
    }

    private void loadFavoriteNews() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            List<News> favoriteNews = activity.getDb().newsDao().loadFavoriteNews();
            binding.rvField.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvField.setAdapter(new FieldAdapter(favoriteNews, updatedNews -> {
                activity.getDb().newsDao().insertAll(updatedNews);
                loadFavoriteNews();
            }));
        }

    }

    @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }
    }
