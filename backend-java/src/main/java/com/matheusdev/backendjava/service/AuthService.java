package com.matheusdev.backendjava.service;

import com.matheusdev.backendjava.dto.ResponseUserDTO;
import com.matheusdev.backendjava.dto.ResponseUserProfileDTO;
import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.entities.UserEntity;
import com.matheusdev.backendjava.repository.ProfileRepository;
import com.matheusdev.backendjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileRepository profileRepository;

	public UserEntity authenticated() {
		Authentication authenticator = SecurityContextHolder.getContext().getAuthentication();
		Jwt jwt = (Jwt) authenticator.getPrincipal();
		String username = jwt.getClaim("username");
		return userRepository.findByEmail(username);
	}

	public void validateSelfOrAdmin(String objectId) {
		UserEntity me = authenticated();
		if (me.hasRole("ROLE_ADMIN")) {
			return;
		}
		if (!me.getObjectId().equals(objectId)) {
			throw new UsernameNotFoundException("Acesso negado. Deve ser o próprio usuário ou admin");
		}
	}

	@Transactional(readOnly = true)
	public ResponseUserProfileDTO getMe() {
		UserEntity user = authenticated();
		ProfileEntity profile = profileRepository.findByUser(user);
		return new ResponseUserProfileDTO(profile);
	}
}
