package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    TextView tvheader;
    EditText ettitle, etdescription, etnote, etpriority, etdate;
    Button btncreate;
    private ArrayList<String> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        init();
        String name = ettitle.getText().toString().trim();
        String desc = etdescription.getText().toString().trim();
        String date = etdate.getText().toString().trim();
        String priority = etpriority.getText().toString().trim();
        String note = etnote.getText().toString().trim();
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.isEmpty()||desc.isEmpty()||date.isEmpty()||
                        priority.isEmpty()||note.isEmpty())
                {
                    Toast.makeText(Dashboard.this, "Something is missing!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(Dashboard.this, FragList.class);
                    startActivity(i);
                    finish();
                }

            }
        });
        tasks = new ArrayList<>();
        tasks.add(name);
        tasks.add(desc);
        tasks.add(date);
        tasks.add(priority);
        tasks.add(note);
    }


    private void init()
    {
        tvheader = findViewById(R.id.tvheading);
        ettitle = findViewById(R.id.ettitle);
        etdescription = findViewById(R.id.etdescription);
        etdate = findViewById(R.id.etDate);
        etpriority = findViewById(R.id.etpriority);
        etnote = findViewById(R.id.etnote);
        btncreate = findViewById(R.id.btncreate);

    }

}