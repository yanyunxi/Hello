package com.swufestu.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {
    EditText dollar;
    EditText euro;
    EditText won;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent intent = getIntent();
        float dollar_rate = intent.getFloatExtra("dollar_rate",0.0f);
        float euro_rate = intent.getFloatExtra("euro_rate",0.0f);
        float won_rate = intent.getFloatExtra("won_rate",0.0f);
        dollar = findViewById(R.id.dollar);
        euro = findViewById(R.id.euro);
        won = findViewById(R.id.won);
        dollar.setText(String.valueOf(dollar_rate));
        euro.setText(String.valueOf(euro_rate));
        won.setText(String.valueOf(won_rate));
    }

    public void save(View view){
        dollar = findViewById(R.id.dollar);
        euro = findViewById(R.id.euro);
        won = findViewById(R.id.won);
        if (dollar.getText().toString().length() > 0 || euro.getText().toString().length() > 0 || won.getText().toString().length() > 0) {
            float rate_dollar = Float.parseFloat(dollar.getText().toString());
            float rate_euro = Float.parseFloat(euro.getText().toString());
            float rate_won = Float.parseFloat(won.getText().toString());
            SharedPreferences sp= getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            Intent intent = new Intent(this,MainActivity3.class);
            editor.putFloat("dollar_rate",rate_dollar);
            editor.putFloat("euro_rate",rate_euro);
            editor.putFloat("won_rate",rate_won);
            editor.apply();
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "请输入金额后再转换", Toast.LENGTH_SHORT).show();
        }
    }
}