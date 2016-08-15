package com.spring.web.demo.logic.service;

import java.util.NoSuchElementException;

interface CrudService<ENTITY, DTO> {

    ENTITY create(DTO params);

    ENTITY update(Long id, DTO params) throws NoSuchElementException;

    void delete(Long id) throws NoSuchElementException;

    ENTITY findById(Long id) throws NoSuchElementException;
}
