package me.dio.footdelas.ui.field;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import me.dio.footdelas.data.local.AppDatabase;
import me.dio.footdelas.databinding.FragmentFieldBinding;
import me.dio.footdelas.ui.MainActivity;
import me.dio.footdelas.ui.adapters.FieldAdapter;

public class FieldFragment extends Fragment {

    private FragmentFieldBinding binding;
    private AppDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FieldViewModel fieldViewModel = new ViewModelProvider(this).get(FieldViewModel.class);

        binding = FragmentFieldBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.srlNews.setOnRefreshListener(fieldViewModel::findNews);


        binding.rvField.setLayoutManager(new LinearLayoutManager(getContext()));
        fieldViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            binding.rvField.setAdapter(new FieldAdapter(news, updatedNews -> {
                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.getDb().newsDao().insertAll(updatedNews);
                }
            }));
        });

        fieldViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case DOING:
                    binding.srlNews.setRefreshing(true);
                    break;
                case DONE:
                    binding.srlNews.setRefreshing(false);
                    break;
                case ERROR:
                    binding.srlNews.setRefreshing(false);
                    Snackbar.make(binding.srlNews, "Network error.", Snackbar.LENGTH_SHORT).show();


            }
        });

            return root;
    }

    private void fieldViewModel() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}