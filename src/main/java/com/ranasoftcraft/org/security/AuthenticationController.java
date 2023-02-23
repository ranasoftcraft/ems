/**
 * 
 */
package com.ranasoftcraft.org.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ranasoftcraft.org.security.entity.Roles;
import com.ranasoftcraft.org.security.entity.Users;
import com.ranasoftcraft.org.security.services.UsersService;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sandeep.rana
 *
 */
@Controller
@Slf4j
public class AuthenticationController {

	@Autowired
	private UsersService usersService;
	
	
	
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");
		return "authentication/login";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		return "authentication/signup";
	}
	
	
	@GetMapping({"/","/welcome"})
	public String welcome(Model model) {
		return "dashboard/welcome";
	}
	
	
	@PostMapping(value = "/signup")
	@ResponseBody
	public Users signUp(@RequestBody Users user, @RequestParam String role) throws NotFoundException {
		final Roles roleModel = usersService.getRole(role);
		Set<Roles> roles = new HashSet<>(); roles.add(roleModel);
		user.setRoles(roles);
		log.info("Going to sign up with username "+ user.getUsername());
		return usersService.signUp(user);		
	}
	
	
	@GetMapping("/afl/auth2.0")
	@ResponseBody
	public OAuth2User afterOAuthLogedin(@AuthenticationPrincipal OAuth2User principal) {
		usersService.findByOauth2IdIgnoreCase(principal.getAttribute("email")).orElseThrow(()-> new UsernameNotFoundException("User " + principal.getAttribute("email") + " not found."));
		return principal;
	}
	
	
	
}
