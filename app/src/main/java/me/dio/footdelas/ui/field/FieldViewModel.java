package me.dio.footdelas.ui.field;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.dio.footdelas.domain.News;

public class FieldViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public FieldViewModel() {
        this.news = new MutableLiveData<>();

        List<News> news = new ArrayList<>();
        news.add(new News("Casa da Libertadores Feminina Escolhida", ""));
        news.add(new News("Hoffmann Túlio Não é Mais Técnico do Palmeiras Feminino", ""));
        news.add(new News("BRASILEIRO FEMININO A3: Sport leva a melhor no clássico contra o Náutico", ""));

        this.news.setValue(news);
    }

    public MutableLiveData<List<News>> getNews() {
        return news;
    }
}