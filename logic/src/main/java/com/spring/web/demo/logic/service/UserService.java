package com.spring.web.demo.logic.service;


import com.spring.web.demo.logic.dto.UserDto;
import com.spring.web.demo.persistent.entity.User;
import org.springframework.data.domain.Page;

public interface UserService extends CrudService<User, UserDto> {

    Page<User> list(Integer pageNumber);
}
