/**
 * 
 */
package com.ranasoftcraft.org.security.services;

import java.util.Optional;

import com.ranasoftcraft.org.security.entity.Roles;
import com.ranasoftcraft.org.security.entity.Users;

import javassist.NotFoundException;

/**
 * @author sandeep.rana
 *
 */
public interface UsersService {

	/**
	 * Sign up the users ... 
	 * @param user request payload ... 
	 * @return
	 */
	Users signUp(Users user);

	Optional<Users> findByUserId(String userName);

	Roles getRole(String roleId) throws NotFoundException;

	Optional<Users> findByOauth2IdIgnoreCase(String oauth2Id);

}
