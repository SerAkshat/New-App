package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeActivity extends AppCompatActivity {

    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DB = new DBHelper(this);

        String email = getIntent().getStringExtra("email");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String name = DB.getUserData(email);
            runOnUiThread(() -> {
                TextView nameTextView = findViewById(R.id.name);
                if (name != null) {
                    nameTextView.setText(name);
                } else {
                    nameTextView.setText("Name not found");
                }
            });
        });

        if (email != null) {
            TextView emailTextView = findViewById(R.id.email);
            emailTextView.setText(email);
        }
    }
}