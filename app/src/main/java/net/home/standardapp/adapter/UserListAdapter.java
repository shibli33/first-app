package net.home.standardapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.home.standardapp.R;
import net.home.standardapp.model.User;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder>{
    private Context mContext;
    //private String  tag;
    private List<User> userList;
    private UserListListener listener;

    public interface UserListListener {
        void onItemClick(User user);
    }

    public UserListAdapter(Context context, List<User> userList,/*String tag,*/ UserListListener listener) {
        this.mContext = context;
        this.userList = userList;
        this.listener = listener;
        //this.tag = tag;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, viewGroup, false);
        return new UserListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        try {
            User user = userList.get(i);
            myViewHolder.buyerName.setText(String.format("Buyer Name :: %s", user.getId()));
            myViewHolder.orderDate.setText(String.format("Order Date :: %s", user.getUsername()));
            myViewHolder.delivaryDate.setText(String.format("Delivery Date :: %s", user.getPassword()));
            myViewHolder.qty.setText(String.format("Order Qty :: %s", user.getEmail()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView buyerName;
        private TextView orderDate;
        private TextView delivaryDate;
        private TextView qty;

        private MyViewHolder(View view) {
            super(view);

            buyerName = view.findViewById(R.id.buyerName);
            orderDate = view.findViewById(R.id.orderDate);
            delivaryDate = view.findViewById(R.id.deliveryDate);
            qty = view.findViewById(R.id.qty);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(userList.get(MyViewHolder.this.getAdapterPosition()));
                }
            });
        }

    }
}
