package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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

        // Binding
        n1EditText = (EditText) findViewById(R.id.n1EditText);
        n2EditText = (EditText) findViewById(R.id.n2EditText);
        resEditText = (EditText) findViewById(R.id.resEditText);
        addButton = (Button) findViewById(R.id.addButton);
        subButton = (Button) findViewById(R.id.subButton);
        mulButton = (Button) findViewById(R.id.mulButton);
        divButton = (Button) findViewById(R.id.divButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        piButton = (Button) findViewById(R.id.piButton);

        // Handlers
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mBound) {
                    Double value1 = validAndGetValue(n1EditText);
                    Double value2 = validAndGetValue(n2EditText);

                    if (value1 != null && value2 != null) {
                        double result = logicService.add(value1, value2);
                        setResult(result);
                    }
                }
            }
        });

        subButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mBound) {
                    Double value1 = validAndGetValue(n1EditText);
                    Double value2 = validAndGetValue(n2EditText);

                    if (value1 != null && value2 != null) {
                        double result = logicService.sub(value1, value2);
                        setResult(result);
                    }
                }
            }
        });

        mulButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mBound) {
                    Double value1 = validAndGetValue(n1EditText);
                    Double value2 = validAndGetValue(n2EditText);

                    if (value1 != null && value2 != null) {
                        double result = logicService.mul(value1, value2);
                        setResult(result);
                    }
                }
            }
        });

        divButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mBound) {
                    Double value1 = validAndGetValue(n1EditText);
                    Double value2 = validAndGetValue(n2EditText);

                    if (value1 != null && value2 != null) {
                        double result = logicService.div(value1, value2);
                        setResult(result);
                    }
                }
            }
        });

        piButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                progressBar.setProgress(0);
                new PiComputeTask().execute();
            }
        });
    }

    private Double validAndGetValue(EditText editText) {
        String temp = editText.getText().toString();
        if (!"".equals(temp)){
            return Double.parseDouble(temp);
        }
        return null;
    }

    private void setResult(double result) {
        resEditText.setText(Double.toString(result));
    }

    LogicService logicService;
    boolean mBound = false;
    private ServiceConnection logicConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            LogicService.LocalBinder binder = (LogicService.LocalBinder) service;
            logicService = binder.getService();
            mBound = true;
            Toast.makeText(MainActivity.this, "Logic Service Connected!",
                    Toast.LENGTH_SHORT).show();
        }
        public void onServiceDisconnected(ComponentName className) {
            logicService = null;
            mBound = false;
            Toast.makeText(MainActivity.this, "Logic Service Disconnected!",
                    Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if (!mBound) {
            this.bindService(new Intent(MainActivity.this,LogicService.class),
                    logicConnection, Context.BIND_AUTO_CREATE);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            mBound = false;
            this.unbindService(logicConnection);
        }
    }

    private class PiComputeTask extends AsyncTask<Void, Integer, Double> {
        protected Double doInBackground(Void... voids) {
            double pi=3.14159;

            double x, y;
            int k = 0;
            int n = 1000000;
            for (int i = 0; i < n; i++){
                x = Math.random();
                y = Math.random();
                if (x*x + y*y <= 1) k++;

                if (i % 100 == 0) {
                    publishProgress(i);
                }
            }
            double p=4.*k/n;

            return pi;
        }

        protected void onPostExecute(Double result) {
            n1EditText.setText(result.toString());
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
    }
}
