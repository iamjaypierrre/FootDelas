package me.dio.footdelas.data.remote;

import java.util.List;

import me.dio.footdelas.domain.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FootDelasAPI {

    @GET("news.json")
    Call<List<News>> getNews();
}
