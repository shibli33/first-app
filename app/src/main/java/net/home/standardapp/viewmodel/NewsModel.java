package net.home.standardapp.viewmodel;

import net.home.standardapp.model.News;

public class NewsModel {
    public String Title, Desc;

    public NewsModel() {
    }

    public NewsModel(News news) {
        this.Title = news.Title;
        this.Desc = news.Desc;
    }
}
