package net.home.standardapp.model;

import android.text.Editable;

public class Country {
    private int countryId;
    private String countryName;
    private String countryLang;
    private int countryPopulation;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryLang() {
        return countryLang;
    }

    public void setCountryLang(String countryLang) {
        this.countryLang = countryLang;
    }

    public int getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(int countryPopulation) {
        this.countryPopulation = countryPopulation;
    }
}
