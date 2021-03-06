package me.dio.footdelas.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import me.dio.footdelas.domain.News;

@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(News updatedNews);

    @Query("SELECT * FROM news WHERE favorite = 1 ")
    List<News> loadFavoriteNews();




}
