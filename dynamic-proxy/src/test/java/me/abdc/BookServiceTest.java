package me.abdc;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

class BookServiceTest {

    BookService bookService = new DefaultBookService();

    BookService bookServiceProxy = new BookServiceProxy(bookService);

    BookService bookServiceDynamicProxy = (BookService) Proxy.newProxyInstance(
            BookService.class.getClassLoader(),
            new Class[]{BookService.class},
            (proxy, method, args) -> {
                System.out.println("before");
                Object invoke = method.invoke(bookService, args);
                System.out.println("after");
                return invoke;
            });

    @Test
    void rent() {
        Book book = new Book();
        book.setTitle("Proxy");
        bookService.rent(book);
        System.out.println();

        bookServiceProxy.rent(book);
        System.out.println();

        bookServiceDynamicProxy.rent(book);
        System.out.println();
    }

}