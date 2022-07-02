package me.dio.footdelas.ui.field;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Database;
import androidx.room.Room;

import me.dio.footdelas.MainActivity;
import me.dio.footdelas.data.local.AppDatabase;
import me.dio.footdelas.databinding.FragmentFieldBinding;
import me.dio.footdelas.domain.News;
import me.dio.footdelas.ui.adapters.FieldAdapter;

public class FieldFragment extends Fragment {

    private FragmentFieldBinding binding;
    private AppDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FieldViewModel fieldViewModel = new ViewModelProvider(this).get(FieldViewModel.class);

        binding = FragmentFieldBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


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

                    break;
                case DONE:
                    break;
                case ERROR:

            }
        });

            return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}