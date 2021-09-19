package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository repository = new ProductRepository();

    private Book book1 = new Book(1, "Yellow arrow", 100, "Pelevin");
    private Book book2 = new Book(2, "Testing dot com", 80, "Savin");
    private Smartphone smartphone1 = new Smartphone(3, "Iphone", 500, "Apple");
    private Smartphone smartphone2 = new Smartphone(4, "Galaxy", 700, "Samsung");
    private Smartphone smartphone3 = new Smartphone(5, "Galaxy1", 750, "Samsung");

    @BeforeEach
    public void setUp() {
        repository.save(book1);
        repository.save(book2);
        repository.save(smartphone1);
        repository.save(smartphone2);
        repository.save(smartphone3);
    }

    @Test
    void shouldFindById() {
        Product actual = repository.findById(1);
        assertEquals(book1, actual);
    }

    @Test
    void shouldRemoveById() {
        Product[] expected = new Product[]{book2, smartphone1, smartphone2, smartphone3};
        Product[] actual = repository.removeById(1);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAll() {
        Product[] expected = new Product[]{book1, book2, smartphone1, smartphone2, smartphone3};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFoundById() {
        Product actual = repository.findById(6);
        assertEquals(null, actual);
    }

    @Test
    public void shouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(6);
        });
    }
}