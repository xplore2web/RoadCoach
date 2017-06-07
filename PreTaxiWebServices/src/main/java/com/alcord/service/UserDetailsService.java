package com.alcord.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Authenticate a user from the database.
 */

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(final String login);

}
