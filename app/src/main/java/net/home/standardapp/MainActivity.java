package net.home.standardapp;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.home.standardapp.model.Country;
import net.home.standardapp.retrofit.ApiInterface;
import net.home.standardapp.retrofit.RetrofitApiClient;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText name, language, population;
    private MaterialButton submit;
    private MaterialButton cancel;
    private MaterialButton add;
    private ApiInterface api;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        Objects.requireNonNull(actionbar).setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        getSupportActionBar().setTitle(getString(R.string.app_name));
        api= RetrofitApiClient.getClient().create(ApiInterface.class);
        name = findViewById(R.id.editPeice);
        language = findViewById(R.id.orderQty);
        population = findViewById(R.id.editNetWeight);
        submit = findViewById(R.id.submit);
        cancel = findViewById(R.id.cancel);
        add = findViewById(R.id.add);
        add.setOnClickListener(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MVVMActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                if(TextUtils.isEmpty(name.getText().toString())){
                    name.setError("Please Select Country Name");
                    name.requestFocus();
                }else if(TextUtils.isEmpty(language.getText().toString())){
                    language.setError("Please Select Language");
                    language.requestFocus();
                }else if(TextUtils.isEmpty(population.getText().toString())){
                    population.setError("Please Select Population");
                    population.requestFocus();
                }else {
                    Country country = new Country();
                    country.setCountryName(name.getText().toString());
                    country.setCountryLang(language.getText().toString());
                    country.setCountryPopulation(Integer.parseInt(population.getText().toString()));
                    Call<Country> call = api.addCountry(country);
                    call.enqueue(new Callback<Country>() {
                        @Override
                        public void onResponse(Call<Country> call, Response<Country> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
                                name.setText("");
                                language.setText("");
                                population.setText("");
                            }
                        }

                        @Override
                        public void onFailure(Call<Country> call, Throwable t) {
                            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
        }
    }
}
