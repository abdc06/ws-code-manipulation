package me.abdc;

public class BookServiceProxy implements BookService {

    private BookService bookService;

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void rent(Book book) {
        System.out.println("before");
        bookService.rent(book);
        System.out.println("after");
    }
}
