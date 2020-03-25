package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private EditText n1EditText;
    private EditText n2EditText;
    private EditText resEditText;
    private Button addButton;
    private Button subButton;
    private Button mulButton;
    private Button divButton;
    private ProgressBar progressBar;
    private Button piButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n1EditText = (EditText) findViewById(R.id.n1EditText);
        n2EditText = (EditText) findViewById(R.id.n2EditText);
        resEditText = (EditText) findViewById(R.id.resEditText);
        addButton = (Button) findViewById(R.id.addButton);
        subButton = (Button) findViewById(R.id.subButton);
        mulButton = (Button) findViewById(R.id.mulButton);
        divButton = (Button) findViewById(R.id.divButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        piButton = (Button) findViewById(R.id.piButton);
    }
}
