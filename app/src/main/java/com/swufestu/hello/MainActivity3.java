package com.swufestu.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
float dollar_rate=0.0f;
float euro_rate=0.0f;
float won_rate=0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        dollar_rate = intent.getFloatExtra("dollar_rate",0.0f);
        euro_rate = intent.getFloatExtra("euro_rate",0.0f);
        won_rate = intent.getFloatExtra("won_rate",0.0f);
    }
    public void click(View btn){
        if(btn.getId() == R.id.con){
            Intent intent = new Intent(this,MainActivity4.class);
            intent.putExtra("dollar_rate",dollar_rate);
            intent.putExtra("euro_rate",euro_rate);
            intent.putExtra("won_rate",won_rate);
            startActivity(intent);
        }
        else{
            Log.i("TAG", "click:");
            EditText rmb = findViewById(R.id.input_rmb);
            TextView result = findViewById(R.id.result);
            String inp = rmb.getText().toString();
            Log.i("TAG", "click:inp=" + inp);
            if (inp.length() > 0) {
                float num = Float.parseFloat(inp);
                float r = 0;
                if (btn.getId() == R.id.button1) {
                    r = num * dollar_rate;
                } else if (btn.getId() == R.id.button2) {
                    r = num * euro_rate;
                } else {
                    r = num * won_rate;
                }
                Log.i("TAG", "click:r=" + r);
                result.setText(String.valueOf(r));
            } else {
                Toast.makeText(this, "请输入金额后再转换", Toast.LENGTH_SHORT).show();
                result.setText("");
            }
        }
    }
}