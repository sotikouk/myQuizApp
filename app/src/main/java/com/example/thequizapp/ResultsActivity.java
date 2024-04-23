package com.example.thequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.example.thequizapp.databinding.ActivityMainBinding;
import com.example.thequizapp.databinding.ActivityResultsBinding;

public class ResultsActivity extends AppCompatActivity {

    ActivityResultsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_results);

        binding.txtAnswer.setText(
                "Your score is: "
                        +MainActivity.result
                        +"/" +MainActivity.totalQuestions);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultsActivity.this
                        , MainActivity.class);
                startActivity(intent);
            }
        });
    }
}