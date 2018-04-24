package com.example.homeproj;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
    int isbn;
    String title;
    Author author;
    MainActivity.Genre genre;

    public Book(int isbn, String title, Author author, MainActivity.Genre genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    protected Book(Parcel in) {
        isbn = in.readInt();
        title = in.readString();
    }

    public int getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public MainActivity.Genre getGenre() {
        return genre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(isbn);
        dest.writeString(title);
    }
}
