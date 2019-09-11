package net.home.standardapp.model;

public class News {
    public String Title, Desc;

    public News(String title, String desc) {
        Title = title;
        Desc = desc;
    }

    public News() {

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
