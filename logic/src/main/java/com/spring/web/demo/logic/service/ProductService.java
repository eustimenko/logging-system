package com.spring.web.demo.logic.service;

import com.spring.web.demo.persistent.entity.Product;

import java.util.*;

public interface ProductService {

    Product create(Product params);

    Product update(Long id, Product params) throws NoSuchElementException;

    void delete(Long id);

    List<Product> list();

    List<Product> listByFilter(Product filter);
}
