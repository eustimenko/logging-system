package com.spring.web.demo.api.controller;

import com.spring.web.demo.logic.service.ProductService;
import com.spring.web.demo.persistent.entity.Product;
import com.wordnik.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(basePath = "/api/product", value = "Products", description = "Operations with Products")
@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "View list of all products", notes = "View list of all products with pagination")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "")})
    @RequestMapping(method = RequestMethod.GET)
    public List<Product> list(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        final Page<Product> products = productService.list(page);
        return products.getContent();
    }

    @ApiOperation(value = "Create new product", notes = "Create new product by json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "")})
    @RequestMapping(method = RequestMethod.POST)
    public Product create(@RequestBody Product params) {
        return productService.create(params);
    }

    @ApiOperation(value = "Update existing product", notes = "Update existing product by id and json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "")})
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Product update(@PathVariable("id") Long id, @RequestBody Product params) throws NoSuchElementException {
        return productService.update(id, params);
    }

    @ApiOperation(value = "Delete product", notes = "Delete product by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "")})
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

    @ApiOperation(value = "Search products", notes = "Search products by filter using json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "")})
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public List<Product> search(@RequestBody Product filter, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        final Page<Product> products = productService.listByFilter(filter, page);
        return products.getContent();
    }
}
