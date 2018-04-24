package com.example.homeproj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.homeproj.storage.BookProvider;
import com.example.homeproj.storage.DummyBookProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    // TODO: 24.04.18 посмотри на модификаторы доступа public, private, default, protected
    int Flag=-1;  //0-n - меняем свойства в позиции n, -1 - новая книга
    ArrayList<String> title_list = new ArrayList<>();
     EditText edit_add;
    ArrayAdapter<String> list_adapter;
    ListView my_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        edit_add = (EditText)  findViewById(R.id.edit_add);
        Button add_b = (Button) findViewById(R.id.add_b);
        my_list = (ListView) findViewById(R.id.my_list);

        Genre g = Genre.Фантастика;
        switch (g) {
            case Фантастика:
                break;
            case Ужасы:
                break;
            case Детектив:
                break;
            case Любовный_роман:
                break;
            case Проза:
                break;
        }

        BookProvider instance = DummyBookProvider.getInstance();
        List<Book> allBooks = instance.getAllBooks();

        list_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, title_list);
        my_list.setAdapter(list_adapter);
        my_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Toast.makeText(getBaseContext(), "Position = " + position+ " Значение ID = "+ id, Toast.LENGTH_SHORT).show();
                Intent intent_mod = new Intent(MainActivity.this,info_book.class);
                intent_mod.putExtra("isbn", book_mas.get(position).isbn);
                intent_mod.putExtra("title",book_mas.get(position).title);
                intent_mod.putExtra("author_fn", book_mas.get(position).author.firstName);
                intent_mod.putExtra("author_ln", book_mas.get(position).author.lastName);
                intent_mod.putExtra("genre", book_mas.get(position).genre.toString());
                Flag = position;
                startActivityForResult(intent_mod,1);
                edit_add.setText("Your name is " + book_mas.get(position).title.toString()+book_mas.get(position).author.lastName);
            }
        });

        new Intent().putExtra("extra_book", new Book());
    }

    List<Book> book_mas = new ArrayList<>();


    // TODO: 24.04.18 энам передалать как обсудили и вынести в отдельный файл
    public enum Genre {
        Фантастика, Ужасы, Детектив, Любовный_роман, Проза
    }

    public void add_b_cick(View view) {
        Intent intent = new Intent(this,info_book.class);
        intent.putExtra("isbn", title_list.size());
        intent.putExtra("title","");
        intent.putExtra("author_fn", "");
        intent.putExtra("author_ln", "");
        intent.putExtra("genre", "");
        Flag = -1;
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        if(Flag==-1) {
            book_mas.add(new Book(Integer.parseInt(data.getStringExtra("isbn")), data.getStringExtra("title"),
                    new Author(data.getStringExtra("author_fn"), data.getStringExtra("author_ln")), Genre.valueOf(data.getStringExtra("genre"))));
            title_list.add(data.getStringExtra("title"));
        }
        else {
            book_mas.set(Flag,new Book(Integer.parseInt(data.getStringExtra("isbn")), data.getStringExtra("title"),
                    new Author(data.getStringExtra("author_fn"), data.getStringExtra("author_ln")), Genre.valueOf(data.getStringExtra("genre"))));
            title_list.set(Flag,data.getStringExtra("title"));

        };
        list_adapter.notifyDataSetChanged();

    }
}