package net.home.standardapp.retrofit;

import net.home.standardapp.model.Country;
import net.home.standardapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("]getMktUsers")
    Call<List<User>> getAllItem();
    @GET("all")
    Call<List<Country>> getAllCountry();
    @PUT("update")
    Call<Country> updateCountry(@Body Country country);
    @POST("add")
    Call<Country> addCountry(@Body Country country);
    @DELETE("{countryId}")
    Call<Void> deleteCountry(@Path("countryId") int countryId);
}
