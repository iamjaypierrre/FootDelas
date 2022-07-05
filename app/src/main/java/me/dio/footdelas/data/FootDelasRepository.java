package me.dio.footdelas.data;

import androidx.room.Room;

import me.dio.footdelas.App;
import me.dio.footdelas.data.local.AppDatabase;
import me.dio.footdelas.data.remote.FootDelasAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FootDelasRepository {

    //region Constantes
    private static final String REMOTE_API_URL = "https://iamjaypierrre.github.io/Foot-Delas-Api/";
    private static final String LOCAL_DB_NAME = "foot-delas";
    //endregion

    //region Atributos: encapsulam o acesso a nossa API (Retrofit) e banco de dados local (Room).
    private FootDelasAPI remoteApi;
    private AppDatabase localDb;

    public FootDelasAPI getRemoteApi() {
        return remoteApi;
    }

    public AppDatabase getLocalDb() {
        return localDb;
    }
    //endregion

    //region Singleton: garante uma instância única dos atributos relacionados ao Retrofit e Room.
    private FootDelasRepository () {
        remoteApi = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FootDelasAPI.class);

        localDb = Room.databaseBuilder(App.getInstance(), AppDatabase.class, LOCAL_DB_NAME).build();
    }

    public static FootDelasRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final FootDelasRepository INSTANCE = new FootDelasRepository();
    }
    //endregion
}

