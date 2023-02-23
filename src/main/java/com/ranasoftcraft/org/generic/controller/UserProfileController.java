/**
 * 
 */
package com.ranasoftcraft.org.generic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ranasoftcraft.org.security.entity.Users;
import com.ranasoftcraft.org.security.services.UsersService;

/**
 * @author sandeep.rana
 *
 */
@RestController
@RequestMapping(path = "/profile")
public class UserProfileController {

	@Autowired
	private UsersService usersService;
	
	@GetMapping(value = "/{userName}")
	public Optional<Users> usersInfo(final @PathVariable String userName) {
		return usersService.findByUserId(userName);
	}
}
