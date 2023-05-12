package com.example.p9.UI.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.p9.R;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button btnSendMessage;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Передача данных в сторонние приложения
        btnSendMessage = (Button) findViewById(R.id.sendMessage);
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "ТУТ ТЕКСТ";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(intent, "OnShare"));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Получения данных из стороннего приложения
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                // Обработка текстовых данных
                String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                if (sharedText != null) {
                    TextView text = findViewById(R.id.textView);
                    text.setText(sharedText);
                }
            } else if (type.startsWith("image/")) {
                // Обработка изображений
                Uri imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
                if (imageUri != null) {
                    //  тут обрабатываться изображение
                }
            }
        }

    }
}