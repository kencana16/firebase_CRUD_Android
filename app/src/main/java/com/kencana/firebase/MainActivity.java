package com.kencana.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.annotations.Nullable;

public class MainActivity extends AppCompatActivity {

    private Button btCreateDB;
    private Button btViewDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCreateDB = (Button) findViewById(R.id.bt_createdata);
        btViewDB = (Button) findViewById(R.id.bt_viewdata);

        btCreateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // kelas yang akan dijalankan ketika tombol Create/Insert Data diklik

                startActivity(FirebaseDBCreate.getActIntent(MainActivity.this));
            }
        });

        btViewDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(FirebaseDBRead.getActIntent(MainActivity.this));
            }
        });

        getSupportActionBar().setTitle("Firebase Realtime Database");
    }
}

