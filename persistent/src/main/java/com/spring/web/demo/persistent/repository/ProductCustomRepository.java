package com.spring.web.demo.persistent.repository;

import com.spring.web.demo.persistent.entity.Product;
import org.springframework.data.domain.*;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ProductCustomRepository {

    Page<Product> findByParameters(Product params, Pageable pageable);
}
