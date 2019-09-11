package net.home.standardapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.home.standardapp.adapter.CountryAdapter;
import net.home.standardapp.adapter.UserListAdapter;
import net.home.standardapp.fragment.UpdateListFragment;
import net.home.standardapp.model.Country;
import net.home.standardapp.model.User;
import net.home.standardapp.retrofit.ApiInterface;
import net.home.standardapp.retrofit.RetrofitApiClient;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements /*UserListAdapter.UserListListener*/CountryAdapter.CountryListListener{

    private RecyclerView recyclerView;
    private ApiInterface api;
    EditText quantity;
    TextView errorMessages;
    //CountryAdapter mAdapter;
    private List<Country> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        recyclerView = findViewById(R.id.list);
        api= RetrofitApiClient.getClient().create(ApiInterface.class);
//        Call<List<User>> call=api.getAllItem();
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                List<User> userList=response.body();
//                addRecyclerView(userList);
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
        Call<List<Country>> call=api.getAllCountry();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                userList=response.body();
                addRecyclerView(userList);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //private void addRecyclerView(List<User> user) {
    private void addRecyclerView(List<Country> user) {
        //UserListAdapter mAdapter = new UserListAdapter(getApplicationContext(), user, this);
        CountryAdapter mAdapter = new CountryAdapter(getApplicationContext(), user, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(null);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

//    @Override
//    public void onItemClick(User user) {
//
//    }

    @Override
    public void onItemClick(final Country country, int position) {
            final Dialog dialog = new Dialog(Objects.requireNonNull(this), R.style.MyAlertDialogStyle);
            dialog.setContentView(R.layout.update_list);
            dialog.setTitle("Custom Alert Dialog");


            Button btnUpdate, btnCancel;

            dialog.show();

            TextView title = dialog.findViewById(R.id.title);
            quantity = dialog.findViewById(R.id.quantity);
            quantity.setText(country.getCountryName());
            //quantity.setText(String.valueOf(country.getCountryId()));
            errorMessages = dialog.findViewById(R.id.error_message);
            btnUpdate = dialog.findViewById(R.id.btnUpdate);
            btnCancel = dialog.findViewById(R.id.cancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (quantity.getText().toString().trim().equals("")) {
                        errorMessages.setVisibility(View.VISIBLE);
                        errorMessages.setText("Please input value for Qty.");
                    } else {
                        //Toast.makeText(LoginActivity.this, "OK", Toast.LENGTH_SHORT).show();
                        country.setCountryName(quantity.getText().toString());
                        Call<Country> call = api.updateCountry(country);
                        call.enqueue(new Callback<Country>() {
                            @Override
                            public void onResponse(Call<Country> call, Response<Country> response) {
                                dialog.dismiss();
                                addRecyclerView(userList);
                            }

                            @Override
                            public void onFailure(Call<Country> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            });


//        UpdateListFragment fragment = new UpdateListFragment();
//        Bundle args = new Bundle();
//        args.putInt("id", country.getCountryId());
//        args.putString("name", country.getCountryName());
//        args.putString("lan", country.getCountryLang());
//        args.putInt("pop", country.getCountryPopulation());
//        fragment.setArguments(args);
//
//        Objects.requireNonNull(getFragmentManager()).beginTransaction().replace(R.id.frame, fragment).addToBackStack(null).commit();

        //Toast.makeText(this, country.getCountryName(), Toast.LENGTH_SHORT).show();
    }

}
