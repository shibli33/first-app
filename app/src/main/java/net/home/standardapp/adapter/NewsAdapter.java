package net.home.standardapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.home.standardapp.R;
import net.home.standardapp.databinding.NewsBinding;
import net.home.standardapp.viewmodel.NewsModel;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{

    //private Context mContext;
    //private String  tag;
    private List<NewsModel> newsList;
    private LayoutInflater layoutInflater;
//    private UserListListener listener;
//
//    public interface UserListListener {
//        void onItemClick(User user);
//    }

    public NewsAdapter(List<NewsModel> newsList) {

        this.newsList = newsList;

        //this.tag = tag;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(layoutInflater==null){
            //layoutInflater = layoutInflater.from(viewGroup.getContext()).inflate(R.layout.mvvm_layout,viewGroup,false);
            layoutInflater = layoutInflater.from(viewGroup.getContext());
        }
        NewsBinding newsBinding = NewsBinding.inflate(layoutInflater,viewGroup,false);
        return new MyViewHolder(newsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        NewsModel newsModel = newsList.get(i);
        //myViewHolder.bind(newsModel);
        myViewHolder.newsBinding.setNewsview(newsModel);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private NewsBinding newsBinding;

        public MyViewHolder(NewsBinding newsBinding) {
            super(newsBinding.getRoot());
            this.newsBinding= newsBinding;
        }
        public void bind(NewsModel newsModel){
            this.newsBinding.setNewsview(newsModel);
        }

        public NewsBinding getNewsBinding(){
            return newsBinding;
        }
    }
}
