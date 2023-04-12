package com.mikronexus.dejavoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mikronexus.dejavoo.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    Runnable runnable;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
          handler = new Handler();
          runnable = new Runnable() {
            @Override
            public void run() {
                // Update date and time
                Date currentDate = new Date();
                String formattedDate = sdf.format(currentDate);
                binding.dat.setText(formattedDate);

                // Schedule next update
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 0);
        binding.proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.mikronexus.cfa", "com.mikronexus.cfa.MainActivity"));
                startActivity(intent);


            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop updating date and time
        handler.removeCallbacks(runnable);
    }
}