package com.spring.web.demo.logic.service;

import java.util.*;

interface CrudService<ENTITY, DTO> {

    ENTITY create(DTO params);

    ENTITY update(Long id, ENTITY params) throws NoSuchElementException;

    void delete(Long id) throws NoSuchElementException;

    void delete(Set<Long> id);

    ENTITY findById(Long id) throws NoSuchElementException;
}
