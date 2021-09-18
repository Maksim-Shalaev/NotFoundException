package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    private ProductRepository repository = new ProductRepository();
    private Manager manager = new Manager(repository);

    private Book book1 = new Book(1, "Yellow arrow", 100, "Pelevin");
    private Book book2 = new Book(2,"Testing dot com", 80, "Savin");
    private Smartphone smartphone1 = new Smartphone(3,"Iphone", 500, "Apple");
    private Smartphone smartphone2  = new Smartphone(4, "Galaxy", 700, "Samsung");
    private Smartphone smartphone3 = new Smartphone(5, "Galaxy1", 750, "Samsung");

    @BeforeEach
    public void setUp(){
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);
    }

    @Test
    void shouldFindById(){
        Product[] expected = {new Book(1,"Yellow arrow", 100, "Pelevin")};
        Product[] actual = repository.findById(1);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveById(){
        Product[] expected = new Product[]{book2, smartphone1, smartphone2, smartphone3};
        Product[] actual = repository.removeById(1);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFoundById(){
        Product[] expected = new Product[]{null};
        Product[] actual = repository.findById(11);
        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldSearchByAuthor(){
        Product[] expected = {new Book(1, "Yellow arrow", 100, "Pelevin")};  // Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy("Pelevin");
        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchByTitle(){
        Product[] expected = {new Book(2,"Testing dot com", 80, "Savin")};
        Product[] actual = manager.searchBy("Testing dot com");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shoulSearchByManufacturer(){
        Product[] expected = {new Smartphone(3,"Iphone", 500, "Apple")};
        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchByPhoneName(){
        Product[] expected = {new Smartphone(4, "Galaxy", 700, "Samsung")};
        Product[] actual = manager.searchBy("Galaxy");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchAllByName(){
        Product[] expected = new Product[]{smartphone2, smartphone3};
        Product[] actual = manager.searchBy("Samsung");
        assertArrayEquals(expected, actual);
    }


    @Test
    void shoulSearchIfAbsent() {
        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("Sony");
        assertArrayEquals(expected, actual);
    }
}