package com.example.sprint2_converter_creus.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.sprint2_converter_creus.R;
import com.example.sprint2_converter_creus.conversion.Category;
import com.example.sprint2_converter_creus.conversion.ConverterEngine;
import com.example.sprint2_converter_creus.conversion.Unit;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class ConversionActivity extends AppCompatActivity {

    private TextView tvCategoryTitle;
    private EditText etInputValue;
    private Spinner spinnerFromUnit;
    private Spinner spinnerToUnit;
    private Button btnConvert;
    private TextView tvResult;
    private Button btnBack;
    private ImageButton btnTheme;

    private Category currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        tvCategoryTitle = findViewById(R.id.tvCategoryTitle);
        etInputValue = findViewById(R.id.etInputValue);
        spinnerFromUnit = findViewById(R.id.spinnerFromUnit);
        spinnerToUnit = findViewById(R.id.spinnerToUnit);
        btnConvert = findViewById(R.id.btnConvert);
        tvResult = findViewById(R.id.tvResult);
        btnBack = findViewById(R.id.btnBack);
        btnTheme = findViewById(R.id.btnTheme);

        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            btnTheme.setImageResource(R.drawable.ic_light);
        } else {
            btnTheme.setImageResource(R.drawable.ic_toggle_theme);
        }

        btnTheme.setOnClickListener(v -> {
            int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

            if (nightMode == Configuration.UI_MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });

        String categoryName = getIntent().getStringExtra("EXTRA_CATEGORY_NAME");
        if (categoryName != null) {
            try {
                currentCategory = Category.valueOf(categoryName);
            } catch (IllegalArgumentException e) {
                currentCategory = Category.DISTANCE;
            }
        } else {
            currentCategory = Category.DISTANCE;
        }

        tvCategoryTitle.setText(currentCategory.getDisplayName() + " Conversion");
        setupSpinners();

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupSpinners() {
        ArrayAdapter<Unit> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                currentCategory.getUnits()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFromUnit.setAdapter(adapter);
        spinnerToUnit.setAdapter(adapter);

        if (currentCategory.getUnits().size() > 1) {
            spinnerToUnit.setSelection(1);
        }
    }

    private void performConversion() {
        String inputText = etInputValue.getText().toString().trim();

        if (inputText.isEmpty()) {
            Snackbar.make(
                    findViewById(android.R.id.content),
                    "Please enter a value to convert.",
                    Snackbar.LENGTH_SHORT
            ).show();
            return;
        }

        double inputValue;
        try {
            inputValue = Double.parseDouble(inputText);
        } catch (NumberFormatException e) {
            Snackbar.make(
                    findViewById(android.R.id.content),
                    "Invalid numeric format. Please check your input.",
                    Snackbar.LENGTH_SHORT
            ).show();
            return;
        }

        Unit fromUnit = (Unit) spinnerFromUnit.getSelectedItem();
        Unit toUnit = (Unit) spinnerToUnit.getSelectedItem();

        if (fromUnit == null || toUnit == null) {
            Snackbar.make(
                    findViewById(android.R.id.content),
                    "Please select both source and target units.",
                    Snackbar.LENGTH_SHORT
            ).show();
            return;
        }

        double result = ConverterEngine.convert(currentCategory, inputValue, fromUnit, toUnit);

        String formattedResult = String.format(Locale.US, "%.4f", result)
                .replaceAll("0*$", "")
                .replaceAll("\\.$", "");

        tvResult.setText(String.format("%s %s = %s %s",
                inputText, fromUnit.getSymbol(), formattedResult, toUnit.getSymbol()));

        Snackbar.make(
                findViewById(android.R.id.content),
                "Conversion completed successfully!",
                Snackbar.LENGTH_SHORT
        ).show();
    }
}