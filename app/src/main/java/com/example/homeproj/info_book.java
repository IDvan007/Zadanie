package com.example.homeproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

// TODO: 24.04.18 Имя должно быть типа BookDetailActivity
public class info_book extends AppCompatActivity {
    // TODO: 24.04.18 именование полей класса. можешь называть с m или без m, см. code style guide
    EditText ed_isbn,ed_title,ed_author_fn,ed_author_ln;
    Spinner genre;
    String[] data = {"Фантастика", "Ужасы", "Детектив", "Любовный_роман", "Проза"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_book);

        ed_isbn = findViewById(R.id.ed_isbn);
        ed_title = findViewById(R.id.ed_title);
        ed_author_fn = findViewById(R.id.ed_author_fn);
        ed_author_ln = findViewById(R.id.ed_author_ln);
        genre = findViewById(R.id.genre);

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(adapter);
        // заголовок
        genre.setPrompt("Title");

        Intent intent = getIntent();
        // TODO: 24.04.18 посмотри чем отличается int и Integer
        Integer isbm = intent.getIntExtra("isbn", 0);
        ed_isbn.setText(isbm.toString());
        ed_title.setText(intent.getStringExtra("title"));
        ed_author_fn.setText(intent.getStringExtra("author_fn"));
        ed_author_ln.setText(intent.getStringExtra("author_ln"));

        // выделяем элемент
        String genre_str =  intent.getStringExtra("genre");
        int position=0;
        for(int i = 0; i < data.length; i++){
            if (data[i].equals(genre_str)) {
                position=i;
            }
        }
        genre.setSelection(position);
    }

    public void bt_save_click(View view) {
        Intent intent_push = new Intent();
        // TODO: 24.04.18 Сделй через Parcelable и через Serializable
        intent_push.putExtra("isbn",ed_isbn.getText().toString() );
        intent_push.putExtra("title",ed_title.getText().toString() );
        intent_push.putExtra("author_fn",ed_author_fn.getText().toString() );
        intent_push.putExtra("author_ln",ed_author_ln.getText().toString() );
        intent_push.putExtra("genre",genre.getSelectedItem().toString() );
        setResult(RESULT_OK, intent_push);
        finish();
    }
}