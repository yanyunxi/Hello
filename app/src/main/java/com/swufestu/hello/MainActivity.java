package com.swufestu.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    TextView score;
    int sco = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
    }
    public void click(View btn){
        if(btn.getId()==R.id.button1){
            sco += 1;
        }else if (btn.getId()==R.id.button2){
            sco += 2;
        }else if (btn.getId()==R.id.button3) {
            sco += 3;
        }else{
            sco=0;
        }
        TextView score = findViewById(R.id.score);
        score.setText(String.valueOf(sco));
    }
}