package me.abdc.di;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerServiceTest {

    @Test
    void getObject() {
        BookRepository bookRepository = ContainerService.getObject(BookRepository.class);

        assertNotNull(bookRepository);

        BookService bookService = ContainerService.getObject(BookService.class);

        assertNotNull(bookService);
        assertNotNull(bookService.getBookRepository());
    }
}