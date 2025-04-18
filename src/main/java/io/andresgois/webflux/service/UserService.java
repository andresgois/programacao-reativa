package io.andresgois.webflux.service;

import io.andresgois.webflux.domain.request.UserRequest;
import io.andresgois.webflux.domain.User;
import io.andresgois.webflux.mapper.UserMapper;
import io.andresgois.webflux.repository.UserRepository;
import io.andresgois.webflux.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public Mono<User> save(final UserRequest request) {
		return userRepository.save(userMapper.toEntity(request));
	}

	public Mono<User> findById(final String id) {
		return handleNotFound(userRepository.findById(id), id);
	}

	public Flux<User> findAll() {
		return userRepository.findAll();
	}
	public Mono<User> update(final String id, final UserRequest request){
		return userRepository.findById(id)
				.map(entity -> userMapper.toEntity(request, entity))
				.flatMap(userRepository::save);
	}

	/* Versão antiga
	public Mono<User> deleteById(final String id) {
		return userRepository.findAndRemove(id)
				.switchIfEmpty(Mono.error(
				new ObjectNotFoundException(String.format("Object not found. Id: %s Type: %s",id,User.class.getSimpleName())))
		);
	}*/
	public Mono<User> deleteById(final String id) {
		return handleNotFound(userRepository.findAndRemove(id), id);
	}

	private <T> Mono<T> handleNotFound(Mono<T> mono, String id){
		return mono.switchIfEmpty(Mono.error(
				new ObjectNotFoundException(
						String.format("Object not found. Id: %s Type: %s",id,User.class.getSimpleName())
				)
		));
	}
}
