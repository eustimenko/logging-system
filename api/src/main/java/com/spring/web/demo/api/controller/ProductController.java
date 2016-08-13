package com.spring.web.demo.api.controller;

import com.spring.web.demo.logic.service.ProductService;
import com.spring.web.demo.persistent.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> list() {
        return productService.list();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product create(@RequestBody Product params) {
        return productService.create(params);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Product update(@PathVariable("id") Long id, @RequestBody Product params) throws NoSuchElementException {
        return productService.update(id, params);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public List<Product> search(@RequestBody Product filter) {
        return productService.listByFilter(filter);
    }
}
