package com.tw.pathashala69.biblioteca.action;

import com.tw.pathashala69.biblioteca.models.Book;

import java.util.List;

// Job: View details of all books
public class BookListViewAction implements Action {
    public List<Book> books;

    public BookListViewAction(List<Book> books) {
        this.books = books;
    }

    @Override
    public void perform() {
        books.forEach(book -> book.print(System.out));
    }
}
