package net.home.standardapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.home.standardapp.adapter.NewsAdapter;
import net.home.standardapp.viewmodel.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class MVVMActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<NewsModel> list;
    private NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        newsAdapter = new NewsAdapter(list);
        recyclerView.setAdapter(newsAdapter);
        setData();
    }
    private  void setData(){
        NewsModel model = new NewsModel();
        model.Title = "First Title";
        model.Desc = "This is First Title";
        list.add(model);

        NewsModel model2 = new NewsModel();
        model2.Title = "Second Title";
        model2.Desc = "This is Second Title";
        list.add(model2);
    }
}
