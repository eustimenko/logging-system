package com.spring.web.demo.logic.service;

import com.spring.web.demo.logic.mock.MockBuilder;
import com.spring.web.demo.persistent.entity.Product;
import com.spring.web.demo.persistent.repository.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

import static org.mockito.Mockito.mock;

@Service
@Transactional
public class DefaultProductService implements ProductService {

    public static final int DEFAULT_PAGE_SIZE = 20;

//    Uncomment this to launch application with production data
//    @Autowired
//    private ProductRepository repository;

    // Remove this to launch application with production data
    private final ProductRepository repository = mock(ProductRepository.class);

    public Product create(Product params) {
        return repository.save(params);
    }

    public Product update(Long id, Product params) throws NoSuchElementException {
        final Product product = repository.findById(id).get();

        product.setTitle(params.getTitle());
        product.setDescription(params.getDescription());
        product.setSku(params.getSku());

        return repository.save(product);
    }

    public void delete(Long id) {
        final Optional<Product> product = repository.findById(id);
        if (product.isPresent()) repository.delete(product.get());
    }

    public Page<Product> list(Integer pageNumber) {
        return repository.findAll(defaultPageRequest(pageNumber));
    }

    private PageRequest defaultPageRequest(Integer pageNumber) {
        return new PageRequest(pageNumber - 1, DEFAULT_PAGE_SIZE, Sort.Direction.ASC, "title");
    }

    public Page<Product> listByFilter(Product filter, Integer pageNumber) {
        return repository.findByParameters(filter, defaultPageRequest(pageNumber));
    }

    /**
     * Remove this method to launch application with production data
     */
    @PostConstruct
    public void initMocks() {
        MockBuilder.build(repository);
    }
}
