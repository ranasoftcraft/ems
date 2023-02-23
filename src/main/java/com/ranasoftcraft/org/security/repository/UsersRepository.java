package com.ranasoftcraft.org.security.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ranasoftcraft.org.security.entity.Users;

@Repository
public interface UsersRepository extends PagingAndSortingRepository<Users, String> {
	
	Optional<Users> findByOauth2IdIgnoreCase(String oauth2Id);
}
