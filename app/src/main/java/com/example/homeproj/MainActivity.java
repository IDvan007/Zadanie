package com.example.homeproj;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
   int Flag=-1;  //0-n - меняем свойства в позиции n, -1 - новая книга
    ArrayList<String> title_list = new ArrayList<>();
     EditText edit_add;
    ArrayAdapter<String> list_adapter;
    ListView my_list;
   public enum Genre {Фантастика, Ужасы, Детектив, Любовный_роман, Проза}
   List<Book> book_mas = new ArrayList<>();


public class Author {
        String firstName, lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

    private class Book {
        int isbn;
        String title;
        Author author;
        Genre genre;

        public Book(int isbn, String title, Author author, Genre genre) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.genre = genre;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        edit_add = (EditText)  findViewById(R.id.edit_add);
        Button add_b = (Button) findViewById(R.id.add_b);
        my_list = (ListView) findViewById(R.id.my_list);

        book_mas.add(new Book(0,"Война и Мир", new Author("Лев", "Толстой"),Genre.Любовный_роман));
        title_list.add("Война и Мир");

        book_mas.add(new Book(1,"Анна Каренина", new Author("Лев", "Толстой"),Genre.Любовный_роман));
        title_list.add("Анна Каренина");

        book_mas.add(new Book(2,"Мастер и Маргарита", new Author("Михаил", "Булгаков"),Genre.Фантастика));
        title_list.add("Мастер и Маргарита");

        book_mas.add(new Book(3,"Приключения Шерлока Холмса", new Author("Артур", "Конан-Дойл"),Genre.Детектив));
        title_list.add("Приключения Шерлока Холмса");

        book_mas.add(new Book(4,"Вий", new Author("Михаил", "Гоголь"),Genre.Ужасы));
        title_list.add("Вий");

        book_mas.add(new Book(5,"Поэма Евгений Онегин", new Author("Александр", "Пушкин"),Genre.Проза));
        title_list.add("Поэма Евгений Онегин");

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