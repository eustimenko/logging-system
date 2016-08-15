package com.spring.web.demo.api.controller;

import com.spring.web.demo.logic.dto.UserDto;
import com.spring.web.demo.logic.exception.*;
import com.spring.web.demo.logic.service.UserService;
import com.spring.web.demo.persistent.entity.User;
import com.wordnik.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(basePath = "/auth", value = "Authentication", description = "Authentication user operations")
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Registration", notes = "Registration user process")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "")})
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public User register(@RequestBody @Valid UserDto params) throws EmailExistsException, LoginExistsException {
        return userService.create(params);
    }
}
