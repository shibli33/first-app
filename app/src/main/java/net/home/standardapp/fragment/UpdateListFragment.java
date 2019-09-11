package net.home.standardapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.home.standardapp.R;

public class UpdateListFragment  extends Fragment implements  View.OnClickListener{

    private String value, value1, value2, value3, value4;
    TextView title, errorMessages;
    EditText quantity;
    //quantity.setText(country.getCountryPopulation());

    Button btnUpdate, btnCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.update_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.title);
        quantity = view.findViewById(R.id.quantity);
        errorMessages = view.findViewById(R.id.error_message);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnCancel = view.findViewById(R.id.cancel);
    }

    @Override
    public void onClick(View v) {

    }
}
