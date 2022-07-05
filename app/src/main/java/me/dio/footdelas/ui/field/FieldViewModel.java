package me.dio.footdelas.ui.field;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.List;

import me.dio.footdelas.data.local.AppDatabase;
import me.dio.footdelas.data.remote.FootDelasAPI;
import me.dio.footdelas.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FieldViewModel extends ViewModel {

    public enum State {
        DOING, DONE, ERROR
    }

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<>();
    private final FootDelasAPI api;

    public FieldViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iamjaypierrre.github.io/Foot-Delas-Api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(FootDelasAPI.class);
        this.findNews();

    }

    public void findNews() {
        state.setValue(State.DOING);
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                    state.setValue(State.DONE);
                } else {
                    state.setValue(State.ERROR);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable error) {
                error.printStackTrace();
                state.setValue(State.ERROR);
            }
        });
    }

    public MutableLiveData<List<News>> getNews() {
        return news;
    }

    public MutableLiveData<State> getState() {
        return this.state;
    }

}

// API que nao funciona, arrumar e entender o pq https://iamjaypierrre.github.io/Foot-Delas-Api/
// API que funciona https://digitalinnovationone.github.io/soccer-news-api/