package com.example.sprint2_converter_creus;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.sprint2_converter_creus.conversion.Category;
import com.example.sprint2_converter_creus.ui.ConversionActivity;
import com.example.sprint2_converter_creus.util.ImageUtils;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCategory;
    private ImageView imgCategory;
    private Button btnContinue;
    private Category selectedCategory;
    private ImageButton btnTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        imgCategory = findViewById(R.id.imgCategory);
        btnContinue = findViewById(R.id.btnContinue);
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

        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(
                this,
                android.R.layout.simple_spinner_item,
                Category.values()
        ) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                android.widget.TextView tv = view.findViewById(android.R.id.text1);
                if (tv != null) {
                    tv.setTextColor(getTextColorPrimary());
                }
                return view;
            }

            @NonNull
            @Override
            public View getDropDownView(int position, View convertView, @NonNull android.view.ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                android.widget.TextView tv = view.findViewById(android.R.id.text1);
                if (tv != null) {
                    tv.setTextColor(getTextColorPrimary());
                }
                return view;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                selectedCategory = (Category) parent.getItemAtPosition(position);
                updateCategoryImage(selectedCategory);
            }
            public void onNothingSelected(AdapterView<?> parent){}
        });

        btnContinue.setOnClickListener(v -> {
            if (selectedCategory != null) {
                Intent intent = new Intent(MainActivity.this, ConversionActivity.class);
                intent.putExtra("EXTRA_CATEGORY_NAME", selectedCategory.name());
                startActivity(intent);
            }
        });
    }
    private void updateCategoryImage(Category category){
        Bitmap bitmap = ImageUtils.decodeBitmapFromResource(this, category.getImageResId());
        imgCategory.setImageBitmap(bitmap);
    }

    private int getTextColorPrimary() {
        int[] attrs = new int[]{android.R.attr.textColorPrimary};
        try (android.content.res.TypedArray ta = obtainStyledAttributes(attrs)) {
            return ta.getColor(0, android.graphics.Color.WHITE);
        }
    }
}