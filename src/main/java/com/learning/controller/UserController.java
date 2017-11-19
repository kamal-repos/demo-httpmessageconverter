package com.learning.controller;

import static com.learning.constant.GlobalConstants.APPLICATION_CSV;

import java.util.Iterator;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.model.User;
import com.learning.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/rest")
public class UserController {

	@Autowired
	UserService userService;

	@ApiOperation(value = "Get users")
	@GetMapping(value = "/users")
	@Produces({ APPLICATION_CSV })
	public Iterator<User> getUsers() {
		return userService.getUsers();
	}

}
