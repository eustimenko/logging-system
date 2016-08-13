package com.spring.web.demo.persistent.repository;

import com.spring.web.demo.persistent.entity.Product;

public interface ProductRepository extends SimpleJpaRepository<Product, Long>, ProductCustomRepository {
}
