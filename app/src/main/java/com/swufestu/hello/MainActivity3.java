package com.swufestu.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity3 extends AppCompatActivity implements Runnable {
float dollar_rate;
float euro_rate;
float won_rate;
Handler handler;
TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what == 6){
                    String str = (String)msg.obj;
                    TextView result = findViewById(R.id.result);
                    result.setText(str);
                }
            }
        };

        Thread thread = new Thread(this);
        thread.start();
        SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);
        dollar_rate = sharedPreferences.getFloat("dollar_rate",0.0f);
        euro_rate = sharedPreferences.getFloat("euro_rate",0.0f);
        won_rate = sharedPreferences.getFloat("won_rate",0.0f);
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

    @Override
    public void run() {
        Log.i("thread 2","Thread Test");
        try {
            URL url = new URL("https://www.usd-cny.com/bankofchina.htm");
            HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
            InputStream in = http.getInputStream();
            String html = InputStream2String(in);
            Log.i("html",html);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage();
        msg.what = 6;
        msg.obj = "hello from thread 2";
        handler.sendMessage(msg);
    }
    public String InputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "UTF-8");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }
}