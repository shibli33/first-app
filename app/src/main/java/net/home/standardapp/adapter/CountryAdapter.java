package net.home.standardapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.home.standardapp.R;
import net.home.standardapp.model.Country;
import net.home.standardapp.retrofit.ApiInterface;
import net.home.standardapp.retrofit.RetrofitApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder>{

    private Context mContext;
    //private String  tag;
    private List<Country> countryList;
    private CountryListListener listener;
    //private DeleteListListener dListener;

    private ApiInterface api;
    int id;

    public interface CountryListListener {
        void onItemClick(Country country, int position);
    }

//    public interface DeleteListListener {
//        void onItemClickDeleted(Country country, int position);
//    }

    public CountryAdapter(Context context, List<Country> countryList,/*String tag,*/ CountryListListener listener) {
        this.mContext = context;
        this.countryList = countryList;
        this.listener = listener;
        //this.tag = tag;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, viewGroup, false);
        api= RetrofitApiClient.getClient().create(ApiInterface.class);
        return new CountryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        try {
            Country country = countryList.get(i);
            myViewHolder.buyerName.setText(String.format("Buyer Name :: %s", country.getCountryId()));
            myViewHolder.orderDate.setText(String.format("Order Date :: %s", country.getCountryName()));
            myViewHolder.delivaryDate.setText(String.format("Delivery Date :: %s", country.getCountryLang()));
            myViewHolder.qty.setText(String.format("Order Qty :: %s", country.getCountryPopulation()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView buyerName;
        private TextView orderDate;
        private TextView delivaryDate;
        private TextView qty;
        private Button delete;

        private MyViewHolder(View view) {
            super(view);

            buyerName = view.findViewById(R.id.buyerName);
            orderDate = view.findViewById(R.id.orderDate);
            orderDate.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_rotate_right_black_24dp, 0, 0, 0);
            orderDate.setCompoundDrawablePadding(25);
            delivaryDate = view.findViewById(R.id.deliveryDate);
            qty = view.findViewById(R.id.qty);
            delete = view.findViewById(R.id.delete);

            //view.setOnClickListener(this);
            orderDate.setOnClickListener(this);
            delete.setOnClickListener(this);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.onItemClick(countryList.get(CountryAdapter.MyViewHolder.this.getAdapterPosition()));
//                }
//            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                    alertbox.setMessage("Want to delete this list ?");
                    alertbox.setTitle("Delete");
                    alertbox.setIcon(R.drawable.ic_rotate_right_black_24dp);

                    alertbox.setNeutralButton("OK",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                   Country country = new Country();

                                    for(int i=0; i<countryList.size();i++){
                                        id = countryList.get(i).getCountryId();
                                    }

                                   // for (final Country demandCHDItems : country.getCountryId()) {

                                   // }

                                   // Call<Void> call = api.deleteCountry(countryList.get(arg1).getCountryId());
                                    Call<Void> call = api.deleteCountry(id);

                                    call.enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                            if(response.isSuccessful()){
                                                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                                                CountryAdapter mAdapter = new CountryAdapter(mContext, countryList, listener);
                                                mAdapter.notifyDataSetChanged();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Void> call, Throwable t) {
                                            Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            });
                    alertbox.show();
                }
            });
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                int position=getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listener.onItemClick(countryList.get(getAdapterPosition()), position);
                    //dListener.onItemClickDeleted(countryList.get(getAdapterPosition()), position);
                }
            }
        }
    }
}
