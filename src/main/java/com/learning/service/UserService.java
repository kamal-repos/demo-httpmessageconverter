package com.learning.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.learning.model.User;

@Service
public class UserService {

	public Iterator<User> getUsers() {
		Collection<User> users = new ArrayList<>();
		for (int i = 0; i <= 4; i++) {
			users.add(new User(1, "kamal", "male"));
		}
		return users.iterator();
	}
}
