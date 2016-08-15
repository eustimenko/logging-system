package com.spring.web.demo.api.controller;

import com.spring.web.demo.logic.dto.UserDto;
import com.spring.web.demo.logic.exception.*;
import com.spring.web.demo.logic.service.UserService;
import com.spring.web.demo.persistent.entity.User;
import com.wordnik.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(basePath = "/api/root", value = "Root's controller", description = "Root operations with Users")
@RestController
@RequestMapping("api/root")
public class RootController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "View list of all users", notes = "View list of all users with pagination")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "")})
    @RequestMapping(method = RequestMethod.GET)
    public List<User> list(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        final Page<User> users = userService.list(page);
        return users.getContent();
    }

    @ApiOperation(value = "Update existing user", notes = "Update existing user by id and json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "")})
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public User update(@PathVariable("id") Long id, @RequestBody UserDto params) throws NoSuchElementException, EmailExistsException, LoginExistsException {
        return userService.update(id, params);
    }

    @ApiOperation(value = "Delete user", notes = "Delete user by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "")})
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws NoSuchElementException {
        userService.delete(id);
    }
}
