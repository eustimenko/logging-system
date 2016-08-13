package com.spring.web.demo.persistent.repository;

import com.spring.web.demo.persistent.entity.Product;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProductCustomRepository {

    List<Product> findByParameters(Product params);
}
