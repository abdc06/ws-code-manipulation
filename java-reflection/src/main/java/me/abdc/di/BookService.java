package me.abdc.di;

public class BookService {

    @MyInject
    private BookRepository bookRepository;

    public BookRepository getBookRepository() {
        return bookRepository;
    }
}
