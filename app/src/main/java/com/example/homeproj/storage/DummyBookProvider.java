package com.example.homeproj.storage;

import android.support.annotation.Nullable;

import com.example.homeproj.Author;
import com.example.homeproj.Book;
import com.example.homeproj.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class DummyBookProvider implements BookProvider {

    // TODO: 24.04.18 Расберись с static, а также inner class и static inner class
    private static BookProvider instance;
    private List<Book> books = new ArrayList<>();

    DummyBookProvider() {
        books.add(new Book(0, "Война и Мир", new Author("Лев", "Толстой"), MainActivity.Genre.Любовный_роман));
        books.add(new Book(1, "Анна Каренина", new Author("Лев", "Толстой"), MainActivity.Genre.Любовный_роман));
        books.add(new Book(2, "Мастер и Маргарита", new Author("Михаил", "Булгаков"), MainActivity.Genre.Фантастика));
        books.add(new Book(3, "Приключения Шерлока Холмса", new Author("Артур", "Конан-Дойл"), MainActivity.Genre.Детектив));
        books.add(new Book(4, "Вий", new Author("Михаил", "Гоголь"), MainActivity.Genre.Ужасы));
        books.add(new Book(5, "Поэма Евгений Онегин", new Author("Александр", "Пушкин"), MainActivity.Genre.Проза));
    }

    public static BookProvider getInstance() {
        if (instance == null) {
            instance = new DummyBookProvider();
        }
        return instance;
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Nullable
    @Override
    public Book getBookByIsbn(int isbn) {
        for (Book book : books) {
            if (book.getIsbn() == isbn) {
                return book;
            }
        }
        return null;
    }
}
