package me.abdc;

import org.junit.jupiter.api.Test;

class BookServiceTest {

    BookService bookService = new DefaultBookService();

    BookService bookServiceProxy = new BookServiceProxy(bookService);

    @Test
    void rent() {
        Book book = new Book();
        book.setTitle("Proxy");
        bookService.rent(book);

        bookServiceProxy.rent(book);
    }

}