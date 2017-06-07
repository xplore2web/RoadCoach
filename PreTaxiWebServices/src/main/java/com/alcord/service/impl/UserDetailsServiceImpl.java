package com.alcord.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.AccountDao;
import com.alcord.model.Account;
import com.alcord.security.UserNotActivatedException;
import com.alcord.service.UserDetailsService;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Inject
	private AccountDao accountDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String login) {
		//log.debug("Authenticating {}", login);
		Account userFromDatabase = null;
		String lowercaseLogin = login.toLowerCase();
		if (lowercaseLogin.contains("@")) {
			userFromDatabase = accountDao.getByEmailId(lowercaseLogin);
		} else {
			userFromDatabase = accountDao.getByPhoneNumber(lowercaseLogin);
		}

		if (userFromDatabase != null) {
			if (!userFromDatabase.getActivated()) {
				throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
			}
			List<GrantedAuthority> grantedAuthorities = userFromDatabase.getRoles().stream()
					.map(authority -> new SimpleGrantedAuthority(authority.getRoleName())).collect(Collectors.toList());
			return new org.springframework.security.core.userdetails.User(userFromDatabase.getAccountName(),
					userFromDatabase.getAccountPassword(), grantedAuthorities);
		} else {
			throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " + "database");
		}
	}

}
