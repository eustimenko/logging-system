package com.spring.web.demo.logic.service;

import com.spring.web.demo.logic.dto.UserDto;
import com.spring.web.demo.logic.exception.*;
import com.spring.web.demo.persistent.entity.User;
import com.spring.web.demo.persistent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class DefaultUserService implements UserService {

    private static final int DEFAULT_PAGE_SIZE = 20;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> list(Integer pageNumber) {
        return repository.findAll(defaultPageRequest(pageNumber));
    }

    private PageRequest defaultPageRequest(Integer pageNumber) {
        return new PageRequest(pageNumber - 1, DEFAULT_PAGE_SIZE, Sort.Direction.ASC, "login");
    }

    public User create(UserDto params) throws EmailExistsException, LoginExistsException {
        final String email = params.getEmail();
        final String login = params.getLogin();

        if (isEmailExists(email)) throw new EmailExistsException(email);
        if (isLoginExists(login)) throw new LoginExistsException(login);

        final User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setFullname(params.getFullname());
        user.setPassword(passwordEncoder.encode(params.getPassword()));

        return repository.save(user);
    }

    private boolean isEmailExists(String email) {
        return repository.findByEmail(email).isPresent();
    }

    private boolean isLoginExists(String login) {
        return repository.findByLogin(login).isPresent();
    }

    public User update(Long id, UserDto params) throws NoSuchElementException, EmailExistsException, LoginExistsException {
        final String email = params.getEmail();
        final String login = params.getLogin();

        if (isEmailExists(email)) throw new EmailExistsException(email);
        if (isLoginExists(login)) throw new LoginExistsException(login);

        return update(get(id), params);
    }

    private User update(User user, UserDto params) {
        user.setFullname(params.getFullname());
        user.setLogin(params.getLogin());
        user.setEmail(params.getEmail());

        return user;
    }

    public void delete(Long id) throws NoSuchElementException {
        repository.delete(get(id));
    }

    public User findById(Long id) throws NoSuchElementException {
        return get(id);
    }

    private User get(Long id) throws NoSuchElementException {
        final Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NoSuchElementException("The user by the following id " + id + " is not found");
        }
    }
}
