package com.spring.web.demo.logic.service;

import com.spring.web.demo.persistent.entity.Product;
import org.springframework.data.domain.Page;

import java.util.NoSuchElementException;

public interface ProductService {

    Product create(Product params);

    Product update(Long id, Product params) throws NoSuchElementException;

    void delete(Long id);

    Page<Product> list(Integer pageNumber);

    Page<Product> listByFilter(Product filter, Integer pageNumber);
}
