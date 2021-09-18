package ru.netology.repository;

import ru.netology.domain.Product;

public class ProductRepository {

    private Product[] items = new Product[0];


    public void save(Product item) {
        int lenght = items.length + 1;
        Product[] tmp = new Product[lenght];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public Product[] findAll() {
        return items;
    }

    public Product[] findById(int id) {

        Product[] t = new Product[1];
        for (Product item : items) {
            if (item.getId() == id) {
                t[0] = item;
            }
        }
        return t;
    }



    public Product[] removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id:" + id + " not found ");
        }
        int lenght = items.length - 1;
        Product[] tmp = new Product[lenght];
        int index = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
        return tmp;
    }
    }
