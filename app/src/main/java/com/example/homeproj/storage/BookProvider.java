package com.example.homeproj.storage;

import android.support.annotation.Nullable;

import com.example.homeproj.Book;

import java.util.List;

public interface BookProvider {
    List<Book> getAllBooks();

    @Nullable
    Book getBookByIsbn(int isbn);

    // TODO: 24.04.18 Вот тут  ↓  есть данные по книге сделай свою реализацию BookProvider - FromJsonBookProvider, который с помощью GSON библиотеки будет отдавать список книг
    // https://github.com/benoitvallon/100-best-books/blob/master/books.json
}
