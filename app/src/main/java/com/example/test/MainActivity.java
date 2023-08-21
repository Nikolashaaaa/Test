package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton car_image;
    private MediaPlayer carSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        car_image = findViewById(R.id.car_image);
        carSound = MediaPlayer.create(this, R.raw.police);

        TextView textView = findViewById(R.id.textView);
        Button btn_second = findViewById(R.id.btn_second);
        btn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(textView.getText().toString(), btn_second);
                showInfoAlert("Do you want close app?");
            }
        });

        car_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPlayButton(carSound);
            }
        });
    }

    private void soundPlayButton(MediaPlayer sound) {
        if(sound.isPlaying()) {
            sound.stop();
//            sound.pause();
        }
        sound.start();
        sound.setLooping(false);
//       sound.seekTo(1000);
    }

    public void btnClick(View v) {
        showInfo(((Button)v).getText().toString(), ((Button) v));
    }

    // Alert Dialog window when you click button 2
    private void showInfoAlert(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Big clue")
                .setMessage(text)
                .setCancelable(false)
                .setPositiveButton("Of course", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Open second activity
    public void startNewActivity(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    private void showInfo(String text, Button btn) {
        btn.setText("You already click"); // Toast message when you click again
        btn.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN)); // Color of button after click
        Toast.makeText(this, text, Toast.LENGTH_LONG).show(); // Toast message after click
    }
}