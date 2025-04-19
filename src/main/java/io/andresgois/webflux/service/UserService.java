package io.andresgois.webflux.service;

import io.andresgois.webflux.domain.request.UserRequest;
import io.andresgois.webflux.domain.User;
import io.andresgois.webflux.mapper.UserMapper;
import io.andresgois.webflux.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public Mono<User> save(final UserRequest request) {
		return userRepository.save(userMapper.toEntity(request));
	}
}
