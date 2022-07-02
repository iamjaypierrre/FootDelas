package me.dio.footdelas.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import me.dio.footdelas.domain.News;

@Database(entities = {News.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();
}