package com.example.scrollview;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText article = findViewById(R.id.article);
        Button addCommentButton = findViewById(R.id.add_comment_button);
        addCommentButton.setOnClickListener(v -> {
            if(!article.isEnabled()){
                article.setEnabled(true);
                article.requestFocus();
                addCommentButton.setText("Guardar");
            }else {
                article.setEnabled(false);
                addCommentButton.setText("Añadir comentario");
                String text = article.getText().toString();
                getSharedPreferences("prefs", MODE_PRIVATE)
                        .edit().putString("article", text).apply();
            }
        });
        String text = getSharedPreferences("prefs", MODE_PRIVATE)
                .getString("article", getString(R.string.article_text));
        article.setText(text);
    }
}