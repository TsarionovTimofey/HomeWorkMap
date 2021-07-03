package com.example.homeworkmap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextSortByCountry = findViewById(R.id.editTextSortByCountry);

        TextView textViewCapital = findViewById(R.id.textViewCapital);
        EditText editTextEnterCountry = findViewById(R.id.editTextEnterCounty);
        EditText editTextEnterCapital = findViewById(R.id.editTextEnterCapital);
        Button button = findViewById(R.id.buttonAdd);
        ListView listViewCountries = findViewById(R.id.listViewCountries);

        ArrayList<Map<String, String>> countries = new ArrayList<>();
        ArrayList<String> dataForList = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataForList);
        listViewCountries.setAdapter(adapter);

        button.setOnClickListener(v -> {
            String country = prepare(editTextEnterCountry);
            String capital = prepare(editTextEnterCapital);
            if (country.equals("") && capital.equals("")) {
                Toast.makeText(this, "Введите страну и столицу", Toast.LENGTH_SHORT).show();
            } else {

                if (country.equals("")) {
                    Toast.makeText(this, "Введите страну", Toast.LENGTH_SHORT).show();
                }
                if (capital.equals("")) {
                    Toast.makeText(this, "Введите столицу", Toast.LENGTH_SHORT).show();
                }
                if (!country.equals("") && !capital.equals("")) {
                    Map<String, String> item = new HashMap<>();
                    item.put("country", country);
                    item.put("capital", capital);
                    countries.add(item);
                    editTextEnterCapital.getText().clear();
                    editTextEnterCountry.getText().clear();

                    String stringItem = country + " - " + capital;
                    dataForList.add(stringItem);
                    adapter.notifyDataSetChanged();
                }
            }
        });


        editTextSortByCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (countries != null) {
                    for (Map<String, String> item : countries) {
                        if (item.get("country").equals(prepare(editTextSortByCountry))) {
                            textViewCapital.setText(item.get("capital"));
                            return;
                        }
                    }
                    if (!textViewCapital.getText().toString().equals(""))
                        textViewCapital.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    static public String prepare(EditText editText) {
        return editText.getText().toString().trim();
    }
}